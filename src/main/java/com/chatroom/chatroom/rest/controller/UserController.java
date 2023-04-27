package com.chatroom.chatroom.rest.controller;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.rest.dto.CreateUserDto;
import com.chatroom.chatroom.rest.dto.UpdateUserDto;
import com.chatroom.chatroom.rest.dto.UserDto;
import com.chatroom.chatroom.rest.mapper.UserMapper;
import com.chatroom.chatroom.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    UserMapper mapper;

    @PostMapping
    public UserDto createUser(@Validated @RequestBody CreateUserDto userDto) {
        User created = userService.create(mapper.fromCreateUserDto(userDto));
        return mapper.toUserDto(created);
    }

    @PatchMapping
    public UserDto updateUser(@RequestHeader("X-User-Id") Long userId,
                              @Validated @RequestBody UpdateUserDto userDto) {
        User updated = userService.update(mapper.fromUpdateUserDto(userDto), userId);
        return mapper.toUserDto(updated);
    }

    @GetMapping("/{userId}")
    public UserDto findUserById(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        return mapper.toUserDto(user);
    }

    @GetMapping
    public List<UserDto> findAllUsers() {
        List<User> users = userService.findAll();
        return mapper.toUserDtoList(users);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@RequestHeader("X-User-Id") Long userToCheckId,
                               @PathVariable("userId") Long userId) {
        userService.deleteById(userToCheckId, userId);
    }

}
