package com.chatroom.chatroom.rest.exception.handler;

import com.chatroom.chatroom.exception.AccessDeniedException;
import com.chatroom.chatroom.rest.exception.ErrorDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccessDeniedExceptionHandlerTest {
    CommonExceptionHandler handler = new CommonExceptionHandler();

    @Test
    void givenAccessDeniedException_whenHandlingException_thenReturnCorrectErrorTitle() {
        AccessDeniedException exception = new AccessDeniedException("Message");

        ErrorDto errorDto = handler.accessDeniedException(exception);

        assertEquals(exception.getTitle(), errorDto.getTitle());
    }

    @Test
    void givenAccessDeniedException_whenHandlingException_thenReturnCorrectErrorMessage() {
        AccessDeniedException exception = new AccessDeniedException("Message");

        ErrorDto errorDto = handler.accessDeniedException(exception);

        assertEquals(exception.getMessage(), errorDto.getMessage());
    }

}