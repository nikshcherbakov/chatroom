package com.chatroom.chatroom.exception.user;

import com.chatroom.chatroom.exception.RuntimeBusinessException;

import lombok.Getter;

@Getter
public class UserBusinessException extends RuntimeBusinessException {

    public UserBusinessException(String message) {
        super(message);
        this.title = "Ошибка создания/обновления пользователя!";
    }

}
