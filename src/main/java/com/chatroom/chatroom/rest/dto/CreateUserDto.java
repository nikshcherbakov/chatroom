package com.chatroom.chatroom.rest.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserDto {

    @NotNull(message = "Имя пользователя является обязательным!")
    String firstName;

    String secondName;

    String lastName;

    @Past(message = "Дата должна быть в прошлом!")
    LocalDate birthDate;

    @Email(message = "Некорректный формат email!")
    String email;

}
