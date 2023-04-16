package com.chatroom.chatroom.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.chatroom.chatroom.TestingUtils;
import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.rest.dto.CreateUserDto;
import com.chatroom.chatroom.rest.dto.UserDto;

public class UserMapperTest {

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void givenNull_whenMappingFromCreateUserDto_thenReturnNull() {
        CreateUserDto createUserDto = null;

        User result = mapper.fromCreateUserDto(createUserDto);

        assertNull(result);
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenIdIsNull() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();

        User result = mapper.fromCreateUserDto(createUserDto);

        assertNull(result.getId());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenFirstNameIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        String expected = createUserDto.getFirstName();

        User result = mapper.fromCreateUserDto(createUserDto);

        assertEquals(expected, result.getFirstName());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenSecondNameIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        String expected = createUserDto.getSecondName();

        User result = mapper.fromCreateUserDto(createUserDto);

        assertEquals(expected, result.getSecondName());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenLastNameIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        String expected = createUserDto.getLastName();

        User result = mapper.fromCreateUserDto(createUserDto);

        assertEquals(expected, result.getLastName());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenBirthDateIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        LocalDate expected = createUserDto.getBirthDate();

        User result = mapper.fromCreateUserDto(createUserDto);

        assertEquals(expected, result.getBirthDate());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenEmailIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        String expected = createUserDto.getEmail();

        User result = mapper.fromCreateUserDto(createUserDto);

        assertEquals(expected, result.getEmail());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenUserChatsEqualEmptySet() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();

        User result = mapper.fromCreateUserDto(createUserDto);

        assertTrue(result.getUserChats().isEmpty());
    }

    @Test
    public void givenNull_whenMappingToUserDto_thenReturnNull() {
        User user = null;

        UserDto result = mapper.toUserDto(user);

        assertNull(result);
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenIdIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        Long expected = user.getId();

        UserDto result = mapper.toUserDto(user);

        assertEquals(expected, result.getId());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenFirstNameIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        String expected = user.getFirstName();

        UserDto result = mapper.toUserDto(user);

        assertEquals(expected, result.getFirstName());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenSecondNameIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        String expected = user.getSecondName();

        UserDto result = mapper.toUserDto(user);

        assertEquals(expected, result.getSecondName());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenLastNameIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        String expected = user.getLastName();

        UserDto result = mapper.toUserDto(user);

        assertEquals(expected, result.getLastName());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenBirthDateIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        LocalDate expected = user.getBirthDate();

        UserDto result = mapper.toUserDto(user);

        assertEquals(expected, result.getBirthDate());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenEmailIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        String expected = user.getEmail();

        UserDto result = mapper.toUserDto(user);

        assertEquals(expected, result.getEmail());
    }

}
