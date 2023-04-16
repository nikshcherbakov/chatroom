package com.chatroom.chatroom.service;

import org.springframework.validation.annotation.Validated;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.validation.user.UserCreation;

public interface UserService {
    
    User create(@Validated(UserCreation.class) User user);

}
