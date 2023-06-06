package com.chatroom.chatroom.exception;

import lombok.Generated;
import lombok.Getter;

@Getter
@Generated
public class AccessDeniedException extends RuntimeBusinessException {
    private final String title;

    public AccessDeniedException(String message) {
        super(message);
        this.title = "Данный пользователь не имеет достпуа к этой операции";
    }
}
