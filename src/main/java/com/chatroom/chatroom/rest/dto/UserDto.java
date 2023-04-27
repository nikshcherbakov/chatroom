package com.chatroom.chatroom.rest.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;

    String firstName;

    String secondName;

    String lastName;

    LocalDate birthDate;

    String email;

}
