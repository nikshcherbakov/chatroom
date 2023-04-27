package com.chatroom.chatroom.rest.exception.handler;

import com.chatroom.chatroom.exception.NotFoundObjectException;
import com.chatroom.chatroom.rest.exception.ErrorDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NotFoundObjectExceptionHandlerTest {

    NotFoundObjectExceptionHandler handler = new NotFoundObjectExceptionHandler();

    @Test
    void givenNotFoundObjectException_whenHandlingException_thenReturnCorrectErrorTitle() {
        NotFoundObjectException exception = new NotFoundObjectException("Message");

        ErrorDto errorDto = handler.notFoundObjectExceptionHandler(exception);

        assertEquals(exception.getTitle(), errorDto.getTitle());
    }

    @Test
    void givenNotFoundObjectException_whenHandlingException_thenReturnCorrectErrorMessage() {
        NotFoundObjectException exception = new NotFoundObjectException("Message");

        ErrorDto errorDto = handler.notFoundObjectExceptionHandler(exception);

        assertEquals(exception.getMessage(), errorDto.getMessage());
    }

}