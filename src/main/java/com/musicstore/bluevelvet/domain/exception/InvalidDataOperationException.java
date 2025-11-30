package com.musicstore.bluevelvet.domain.exception;

import com.musicstore.bluevelvet.infrastructure.entity.Category;

public class InvalidDataOperationException extends RuntimeException{

    public InvalidDataOperationException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidDataOperationException(String message) {
        super(message);
    }
}
