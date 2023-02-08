package com.patika.weatherapi.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private int httpStatus;
    private String message;

    public Result(int httpStatus) {
        this.httpStatus = httpStatus;
    }

}
