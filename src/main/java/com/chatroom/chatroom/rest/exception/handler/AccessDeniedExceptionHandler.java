package com.chatroom.chatroom.rest.exception.handler;

import com.chatroom.chatroom.exception.AccessDeniedException;
import com.chatroom.chatroom.rest.exception.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccessDeniedExceptionHandler {
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorDto accessDeniedException(AccessDeniedException exception) {
        return ErrorDto.forException(exception);
    }
}
