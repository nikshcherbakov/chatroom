package com.chatroom.chatroom.rest.mapper;

import com.chatroom.chatroom.TestingUtils;
import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.rest.dto.CreateUserDto;
import com.chatroom.chatroom.rest.dto.UpdateUserDto;
import com.chatroom.chatroom.rest.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void givenNull_whenMappingFromCreateUserDto_thenReturnNull() {
        CreateUserDto createUserDto = null;

        User result = mapper.fromDto(createUserDto);

        assertNull(result);
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenIdIsNull() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();

        User result = mapper.fromDto(createUserDto);

        assertNull(result.getId());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenFirstNameIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        String expected = createUserDto.getFirstName();

        User result = mapper.fromDto(createUserDto);

        assertEquals(expected, result.getFirstName());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenSecondNameIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        String expected = createUserDto.getSecondName();

        User result = mapper.fromDto(createUserDto);

        assertEquals(expected, result.getSecondName());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenLastNameIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        String expected = createUserDto.getLastName();

        User result = mapper.fromDto(createUserDto);

        assertEquals(expected, result.getLastName());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenBirthDateIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        LocalDate expected = createUserDto.getBirthDate();

        User result = mapper.fromDto(createUserDto);

        assertEquals(expected, result.getBirthDate());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenEmailIsMappedCorrectly() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();
        String expected = createUserDto.getEmail();

        User result = mapper.fromDto(createUserDto);

        assertEquals(expected, result.getEmail());
    }

    @Test
    public void givenUserDto_whenMappingFromCreateUserDto_thenUserChatsEqualEmptySet() {
        CreateUserDto createUserDto = TestingUtils.createUserCreateDto();

        User result = mapper.fromDto(createUserDto);

        assertTrue(result.getUserChats().isEmpty());
    }

    @Test
    public void givenNull_whenMappingToUserDto_thenReturnNull() {
        User user = null;

        UserDto result = mapper.toDto(user);

        assertNull(result);
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenIdIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        Long expected = user.getId();

        UserDto result = mapper.toDto(user);

        assertEquals(expected, result.getId());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenFirstNameIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        String expected = user.getFirstName();

        UserDto result = mapper.toDto(user);

        assertEquals(expected, result.getFirstName());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenSecondNameIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        String expected = user.getSecondName();

        UserDto result = mapper.toDto(user);

        assertEquals(expected, result.getSecondName());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenLastNameIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        String expected = user.getLastName();

        UserDto result = mapper.toDto(user);

        assertEquals(expected, result.getLastName());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenBirthDateIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        LocalDate expected = user.getBirthDate();

        UserDto result = mapper.toDto(user);

        assertEquals(expected, result.getBirthDate());
    }

    @Test
    public void givenUserDto_whenMappingToUserDto_thenEmailIsMapped() {
        User user = TestingUtils.createTestUser(1L);
        String expected = user.getEmail();

        UserDto result = mapper.toDto(user);

        assertEquals(expected, result.getEmail());
    }

    @Test
    public void givenNull_whenMappingFromUpdateUserDto_thenReturnNull() {
        UpdateUserDto updateUserDto = null;

        User result = mapper.fromDto(updateUserDto);

        assertNull(result);
    }

    @Test
    public void givenUserDto_whenMappingFromUpdateUserDto_thenIdIsNotNull() {
        UpdateUserDto updateUserDto = TestingUtils.createUserUpdateDto(1L);

        User result = mapper.fromDto(updateUserDto);

        assertEquals(1L, result.getId());
    }

    @Test
    public void givenUserDto_whenMappingFromUpdateUserDto_thenFirstNameIsMappedCorrectly() {
        UpdateUserDto updateUserDto = TestingUtils.createUserUpdateDto(1L);
        String expected = updateUserDto.getFirstName();

        User result = mapper.fromDto(updateUserDto);

        assertEquals(expected, result.getFirstName());
    }

    @Test
    public void givenUserDto_whenMappingFromUpdateUserDto_thenSecondNameIsMappedCorrectly() {
        UpdateUserDto updateUserDto = TestingUtils.createUserUpdateDto(1L);
        String expected = updateUserDto.getSecondName();

        User result = mapper.fromDto(updateUserDto);

        assertEquals(expected, result.getSecondName());
    }

    @Test
    public void givenUserDto_whenMappingFromUpdateUserDto_thenLastNameIsMappedCorrectly() {
        UpdateUserDto updateUserDto = TestingUtils.createUserUpdateDto(1L);
        String expected = updateUserDto.getLastName();

        User result = mapper.fromDto(updateUserDto);

        assertEquals(expected, result.getLastName());
    }

    @Test
    public void givenUserDto_whenMappingFromUpdateUserDto_thenBirthDateIsMappedCorrectly() {
        UpdateUserDto updateUserDto = TestingUtils.createUserUpdateDto(1L);
        LocalDate expected = updateUserDto.getBirthDate();

        User result = mapper.fromDto(updateUserDto);

        assertEquals(expected, result.getBirthDate());
    }

    @Test
    public void givenUserDto_whenMappingFromUpdateUserDto_thenEmailIsMappedCorrectly() {
        UpdateUserDto updateUserDto = TestingUtils.createUserUpdateDto(1L);
        String expected = updateUserDto.getEmail();

        User result = mapper.fromDto(updateUserDto);

        assertEquals(expected, result.getEmail());
    }

    @Test
    public void givenNullList_whenMappingFromUpdateUserDtoList_thenReturnNull() {
        List<User> list = null;

        List<UserDto> result = mapper.toDtoList(list);

        assertNull(result);
    }

    @Test
    public void givenUpdateUserDtoList_whenMappingFromUpdateUserDtoList_thenReturnList() {
        User user = TestingUtils.createTestUser(1L);
        List<User> list = List.of(user);

        List<UserDto> result = mapper.toDtoList(list);

        assertEquals(1, result.size());
    }


}
