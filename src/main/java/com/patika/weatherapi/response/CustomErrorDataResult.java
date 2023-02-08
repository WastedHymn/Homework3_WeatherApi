package com.patika.weatherapi.response;

public class CustomErrorDataResult extends DataResult {
    public CustomErrorDataResult(Object data, int statusCode, String message) {
        super(data, statusCode, message);
    }

    public CustomErrorDataResult(Object data, int statusCode) {
        super(statusCode, data);
    }

    public CustomErrorDataResult(String message, int statusCode) {
        super(null, statusCode, message);
    }
}
