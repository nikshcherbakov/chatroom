package com.chatroom.chatroom.service;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.validation.user.UserCreation;
import com.chatroom.chatroom.validation.user.UserUpdate;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface UserService {

    User create(@Validated(UserCreation.class) User user);

    User update(@Validated(UserUpdate.class) User user, Long userId);

    User findById(Long userId);

    List<User> findAll();

    void deleteById(Long userToCheckId, Long userId);

}
