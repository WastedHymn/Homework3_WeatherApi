package com.patika.weatherapi.advice;

import com.patika.weatherapi.response.CustomErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@ControllerAdvice
public class WeatherControllerAdvice {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomErrorResult> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String type = Objects.requireNonNull(ex.getRequiredType()).getSimpleName();
        Object value = ex.getValue();
        String message = String.format("The Input should be a valid '%s' but the input '%s' isn't. Please enter valid values",
                type, value);
        return new ResponseEntity<>(new CustomErrorResult(400, message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<CustomErrorResult> handleHttpClientError(HttpClientErrorException ex){
        int statusCode = ex.getRawStatusCode();
        //Get error message
        String exceptionMes = Objects.requireNonNull(ex.getMessage())
                .split("message\":")[1]
                .replaceAll("\"", "")
                .replaceAll("}", "");
        return new ResponseEntity<>(new CustomErrorResult(statusCode, exceptionMes), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResult> handleRuntimeException(RuntimeException ex){
        return new ResponseEntity<>(new CustomErrorResult(500, "Something bad happened on the server side."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<CustomErrorResult> handleNullPointerException(RuntimeException ex){
        return new ResponseEntity<>(new CustomErrorResult(500, "Something bad happened on the server side."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
