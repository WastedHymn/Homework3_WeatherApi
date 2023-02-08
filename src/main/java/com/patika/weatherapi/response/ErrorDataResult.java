package com.patika.weatherapi.response;

public class ErrorDataResult extends DataResult {
    public ErrorDataResult(Object data, String message) {
        super(data, 500, message);
    }

    public ErrorDataResult(Object data) {
        super(500, data);
    }

    public ErrorDataResult(String message) {
        super(null, 500, message);
    }
}
