package com.patika.weatherapi.response;

public class SuccessfulResult extends Result {

    public SuccessfulResult() {
        super(200);
    }

    public SuccessfulResult(String message) {
        super(200, message);
    }
}
