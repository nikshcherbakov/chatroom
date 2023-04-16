package com.chatroom.chatroom.exception;

import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Generated
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class RuntimeBusinessException extends RuntimeException {

    String title;

    public RuntimeBusinessException(String message) {
        super(message);
        this.title = "Ошибка использования сервиса!";
    }

    public RuntimeBusinessException(String title, String message) {
        super(message);
        this.title = title;
    }

}
