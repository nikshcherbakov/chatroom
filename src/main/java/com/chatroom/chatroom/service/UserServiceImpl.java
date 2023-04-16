package com.chatroom.chatroom.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.exception.user.UserBusinessException;
import com.chatroom.chatroom.repository.UserRepository;
import com.chatroom.chatroom.validation.user.UserCreation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository repository;

    @Override
    public User create(@Validated(UserCreation.class) User user) {
        String email = user.getEmail();
        ensureEmailNotOccupied(email);
        return repository.save(user);
    }

    private void ensureEmailNotOccupied(String email) {
        if (email != null && repository.existsByEmail(email)) {
            throw new UserBusinessException("Пользователь с указанным email уже существует!");
        }
    }
    
}
