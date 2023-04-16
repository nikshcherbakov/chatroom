package com.chatroom.chatroom.rest.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.rest.dto.CreateUserDto;
import com.chatroom.chatroom.rest.dto.UserDto;
import com.chatroom.chatroom.rest.mapper.UserMapper;
import com.chatroom.chatroom.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    UserMapper mapper;
    
    @PostMapping("/users")
    public UserDto createUser(@Validated @RequestBody CreateUserDto userDto) {
        User created = userService.create(mapper.fromCreateUserDto(userDto));
        return mapper.toUserDto(created);
    }

}
