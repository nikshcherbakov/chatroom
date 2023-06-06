package com.chatroom.chatroom.rest.controller;

import com.chatroom.chatroom.rest.dto.CreateUserDto;
import com.chatroom.chatroom.rest.dto.UpdateUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.chatroom.chatroom.TestingUtils.createUserCreateDto;
import static com.chatroom.chatroom.TestingUtils.createUserUpdateDto;
import static java.time.temporal.ChronoUnit.YEARS;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class UserControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void givenUserWithoutFirstName_whenCreatingUser_thenReturnStatus400() throws Exception {
        CreateUserDto requestDto = createUserCreateDto();
        requestDto.setFirstName(null);

        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void givenUserWithIncorrectEmail_whenCreatingUser_thenReturnStatus400() throws Exception {
        CreateUserDto requestDto = createUserCreateDto();
        requestDto.setEmail("incorrectEmailFormat");

        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void givenUserWithIncorrectBirthDate_whenCreatingUser_thenReturnStatus400() throws Exception {
        CreateUserDto requestDto = createUserCreateDto();
        requestDto.setBirthDate(LocalDate.now().plus(1, YEARS));

        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void givenValidUser_whenCreatingUser_thenReturnSavedUser() throws Exception {
        CreateUserDto requestDto = createUserCreateDto();

        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    public void givenUserWithoutId_whenUpdatingUser_thenReturnStatus400() throws Exception {
        UpdateUserDto requestDto = createUserUpdateDto(null);

        mockMvc.perform(put("/users")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void givenWrongEmail_whenUpdatingUser_thenReturnStatus400() throws Exception {
        CreateUserDto requestCreateDto = createUserCreateDto();
        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestCreateDto))
                        .contentType("application/json"))
                .andDo(print());
        CreateUserDto requestCreateDto2 = createUserCreateDto();
        requestCreateDto2.setEmail("newEmail@mail.com");
        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestCreateDto2))
                        .contentType("application/json"))
                .andDo(print());

        UpdateUserDto requestDto = createUserUpdateDto(1L);
        mockMvc.perform(put("/users")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void givenNotExistingObject_whenUpdatingUser_thenReturnStatus404() throws Exception {
        UpdateUserDto requestDto = createUserUpdateDto(1L);
        mockMvc.perform(put("/users")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void givenValidUser_whenUpdatingUser_thenReturnUser() throws Exception {
        CreateUserDto requestCreateDto = createUserCreateDto();
        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestCreateDto))
                        .contentType("application/json"))
                .andDo(print());

        UpdateUserDto requestDto = createUserUpdateDto(1L);
        mockMvc.perform(put("/users")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    public void givenNotExistingUserId_whenFindingById_thenReturnStatus404() throws Exception {
        mockMvc.perform(get("/users/{userId}", 1L)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void givenValidUserId_whenFindingById_thenReturnUserByGivenId() throws Exception {
        CreateUserDto requestCreateDto = createUserCreateDto();
        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestCreateDto))
                        .contentType("application/json"))
                .andDo(print());

        mockMvc.perform(get("/users/{userId}", 1L)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    public void given1User_whenFindingAll_thenReturnListOfUser() throws Exception {
        CreateUserDto requestCreateDto = createUserCreateDto();
        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestCreateDto))
                        .contentType("application/json"))
                .andDo(print());

        mockMvc.perform(get("/users")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenNotExistingUserId_whenDeletingById_thenReturnStatus404() throws Exception {
        mockMvc.perform(delete("/users/{userId}", 1L)
                        .contentType("application/json")
                        .header("X-User-Id", 1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void givenValidUserId_whenDeletingById_thenReturnUserByGivenId() throws Exception {
        CreateUserDto requestCreateDto = createUserCreateDto();
        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(requestCreateDto))
                        .contentType("application/json"))
                .andDo(print());

        mockMvc.perform(delete("/users/{userId}", 1L)
                        .contentType("application/json")
                        .header("X-User-Id", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
