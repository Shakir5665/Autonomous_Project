package com.processmind.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProcessMindException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final Object[] args;

    public ProcessMindException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCode = null;
        this.args = null;
    }

    public ProcessMindException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = null;
        this.args = null;
    }

    public ProcessMindException(String message, HttpStatus httpStatus, String errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.args = null;
    }

    public ProcessMindException(String message, HttpStatus httpStatus, String errorCode, Object[] args) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.args = args;
    }
}