package com.janmokrackispringproject.responses;

public class BadRequestResponse extends Response{
    public BadRequestResponse(String message) {
        super(400, message);
    }
}
