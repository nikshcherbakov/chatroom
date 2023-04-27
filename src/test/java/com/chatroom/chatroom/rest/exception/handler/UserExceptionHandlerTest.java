package com.chatroom.chatroom.rest.exception.handler;

import com.chatroom.chatroom.exception.user.UserBusinessException;
import com.chatroom.chatroom.rest.exception.ErrorDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserExceptionHandlerTest {

    UserExceptionHandler handler = new UserExceptionHandler();

    @Test
    void givenEmailAlreadyOccupiedException_whenHandlingException_thenReturnCorrectErrorTitle() {
        UserBusinessException exception = new UserBusinessException("Message");

        ErrorDto errorDto = handler.userBusinessExceitionHandler(exception);

        assertEquals(exception.getTitle(), errorDto.getTitle());
    }

    @Test
    void givenEmailAlreadyOccupiedException_whenHandlingException_thenReturnCorrectErrorMessage() {
        UserBusinessException exception = new UserBusinessException("Message");

        ErrorDto errorDto = handler.userBusinessExceitionHandler(exception);

        assertEquals(exception.getMessage(), errorDto.getMessage());
    }

}
