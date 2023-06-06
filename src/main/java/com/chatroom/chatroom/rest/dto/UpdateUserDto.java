package com.chatroom.chatroom.rest.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserDto {
    @NotNull(message = "Id пользователя является обязательным!")
    Long id;
    @NotNull
    String firstName;

    String secondName;

    String lastName;

    @Past(message = "Дата должна быть в прошлом!")
    @Nullable
    LocalDate birthDate;

    @Email(message = "Некорректный формат email!")
    @Nullable
    String email;
}
