package com.chatroom.chatroom.rest.exception.handler;

import com.chatroom.chatroom.exception.NotFoundObjectException;
import com.chatroom.chatroom.rest.exception.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundObjectExceptionHandler {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundObjectException.class)
    public ErrorDto notFoundObjectExceptionHandler(NotFoundObjectException exception) {
        return ErrorDto.forException(exception);
    }
}
