package com.chatroom.chatroom.rest.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

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
