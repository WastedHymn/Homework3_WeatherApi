package com.patika.weatherapi.response;

public class DataResult<T> extends Result {

    private T data;

    public DataResult(T data, int statusCode, String message) {
        super(statusCode, message);
        this.data = data;
    }

    public DataResult(int statusCode, T data) {
        super(statusCode);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
