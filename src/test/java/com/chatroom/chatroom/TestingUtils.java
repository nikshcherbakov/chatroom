package com.chatroom.chatroom;

import static java.time.temporal.ChronoUnit.YEARS;

import java.time.LocalDate;
import java.util.HashSet;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.rest.dto.CreateUserDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    public static LocalDate dateInPast() {
        return LocalDate.now().minus(10, YEARS);
    }

}
