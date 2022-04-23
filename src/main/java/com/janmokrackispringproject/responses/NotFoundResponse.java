package com.janmokrackispringproject.responses;

public class NotFoundResponse  extends Response {

    public NotFoundResponse(Object data) {
        super(404, data);
    }
}
