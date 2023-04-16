package com.chatroom.chatroom.rest.controller;
    
import static com.chatroom.chatroom.TestingUtils.createUserCreateDto;
import static java.time.temporal.ChronoUnit.YEARS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.chatroom.chatroom.rest.dto.CreateUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
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

}
