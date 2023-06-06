package com.chatroom.chatroom;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.rest.dto.CreateUserDto;
import com.chatroom.chatroom.rest.dto.UpdateUserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;

import static java.time.temporal.ChronoUnit.YEARS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestingUtils {

    public static User createTestUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setFirstName("Арсен");
        user.setSecondName("Викторович");
        user.setLastName("Зип-Зипуля");
        user.setBirthDate(dateInPast());
        user.setEmail("email@mail.come");
        user.setUserChats(new HashSet<>());
        return user;
    }

    public static CreateUserDto createUserCreateDto() {
        CreateUserDto dto = new CreateUserDto();
        dto.setBirthDate(TestingUtils.dateInPast());
        dto.setEmail("email@mail.com");
        dto.setFirstName("Иван");
        dto.setSecondName("Александрович");
        dto.setLastName("Терин");
        return dto;
    }

    public static UpdateUserDto createUserUpdateDto(Long id) {
        UpdateUserDto dto = new UpdateUserDto();
        dto.setId(id);
        dto.setEmail("newEmail@mail.com");
        dto.setFirstName("newName");
        return dto;
    }

    public static LocalDate dateInPast() {
        return LocalDate.now().minus(10, YEARS);
    }

}
