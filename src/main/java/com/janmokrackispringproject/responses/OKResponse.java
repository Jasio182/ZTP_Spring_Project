package com.janmokrackispringproject.responses;

public class OKResponse extends Response{

    public OKResponse(Object data) {
        super(200, data);
    }
}
