package com.chatroom.chatroom.rest.exception;

import com.chatroom.chatroom.exception.RuntimeBusinessException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorDto {

    String title;

    String message;

    public static ErrorDto forException(RuntimeBusinessException exception) {
        return new ErrorDto(exception.getTitle(), exception.getMessage());
    }

}
