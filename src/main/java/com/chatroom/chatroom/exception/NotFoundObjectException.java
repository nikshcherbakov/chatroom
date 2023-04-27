package com.chatroom.chatroom.exception;

import lombok.Generated;
import lombok.Getter;

@Getter
@Generated
public class NotFoundObjectException extends RuntimeBusinessException {
    private final String title;

    public NotFoundObjectException(String message) {
        super(message);
        this.title = "По данному id объект не найден!";
    }
}
