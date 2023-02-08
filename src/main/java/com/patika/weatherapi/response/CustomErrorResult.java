package com.patika.weatherapi.response;

public class CustomErrorResult extends Result{
    public CustomErrorResult(int statusCode, String message) {
        super(statusCode, message);
    }

    public CustomErrorResult(int statusCode) {
        super(statusCode);
    }
}
