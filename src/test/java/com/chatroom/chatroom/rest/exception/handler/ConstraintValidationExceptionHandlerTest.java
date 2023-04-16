package com.chatroom.chatroom.rest.exception.handler;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.chatroom.chatroom.rest.exception.ErrorDto;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConstraintValidationExceptionHandlerTest {

    ConstraintValidationExceptionsHandler handler = new ConstraintValidationExceptionsHandler();

    @Test
    public void givenMethodArgumentNotValidException_whenHandlingException_thenReturnErrorDto() {
        MethodArgumentNotValidException exception = makeException();

        ErrorDto error = handler.handleConstraintViolation(exception);

        assertNotNull(error.getMessage());
    }

    private MethodArgumentNotValidException makeException() {
        ObjectError objectError = new ObjectError("objName", "defaultMessage");

        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);
        bindingResult.addError(objectError);

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        return exception;
    }

}
