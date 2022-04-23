package com.janmokrackispringproject.responses;

public class UnauthorizedResponse extends Response{
    public UnauthorizedResponse(String data) {
        super(401, data);
    }
}
