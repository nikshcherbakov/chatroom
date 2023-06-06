package com.chatroom.chatroom.rest.exception.handler;

import com.chatroom.chatroom.rest.exception.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Обработчик ошибок, связанных с @Valid Или @Validated
 */
@RestControllerAdvice
public class ConstraintValidationExceptionsHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleConstraintViolation(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return new ErrorDto("Ошибка валидации входных данных!", message);
    }

}
