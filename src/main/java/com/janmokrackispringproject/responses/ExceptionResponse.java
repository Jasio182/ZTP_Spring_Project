package com.janmokrackispringproject.responses;

public class ExceptionResponse extends Response {

    public ExceptionResponse(String message) {
        super(500, message);
    }
}
