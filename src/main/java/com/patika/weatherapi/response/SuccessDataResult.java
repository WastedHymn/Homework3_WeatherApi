package com.patika.weatherapi.response;

public class SuccessDataResult extends DataResult{
    public SuccessDataResult(Object data, String message) {
        super(data, 200, message);
    }

    public SuccessDataResult(Object data) {
        super(200, data);
    }

}
