package com.patika.weatherapi.controller;

import com.patika.weatherapi.model.ForecastRoot;
import com.patika.weatherapi.response.CustomErrorDataResult;
import com.patika.weatherapi.response.DataResult;
import com.patika.weatherapi.response.ErrorDataResult;
import com.patika.weatherapi.response.SuccessDataResult;
import com.patika.weatherapi.service.WeatherService;
import com.patika.weatherapi.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weatherApi")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/current/byLatitudeLongitude")
    public ResponseEntity<DataResult> getCurrentWeather(
            @RequestParam(name = "lon") float lon,
            @RequestParam(name = "lat") float lat
    ) {
        ForecastRoot forecastRoot = weatherService.getCurrentWeather(lat, lon);
        if (forecastRoot != null) {
            return new ResponseEntity<>(
                    new SuccessDataResult(forecastRoot, ApiUtils.MES_SUCCESSFUL_CURRENTFORECAST),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                new ErrorDataResult("Something went wrong."),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @GetMapping("/current/byCityName")
    public ResponseEntity<DataResult> getCurrentWeather(@RequestParam(name = "cityName") String cityName) {
        //Check if city name only contains letters, whitespaces and -.
        if (cityName.matches(ApiUtils.CITY_NAME_REGEX)) {
            ForecastRoot forecastRoot = weatherService.getCurrentWeather(cityName);
            if (forecastRoot != null) {
                return new ResponseEntity<>(
                        new SuccessDataResult(forecastRoot, ApiUtils.MES_SUCCESSFUL_CURRENTFORECAST),
                        HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    new ErrorDataResult("Something went wrong."),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(
                new CustomErrorDataResult(ApiUtils.CITY_NAME_ERROR, 400),
                HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/weekly/byCityName")
    public ResponseEntity<DataResult> getWeeklyForecast(@RequestParam(name = "cityName") String cityName) {
        if (cityName.matches(ApiUtils.CITY_NAME_REGEX)) {
            ForecastRoot forecastRoot = weatherService.getWeeklyForecast(cityName);
            return new ResponseEntity<>(
                    new SuccessDataResult(forecastRoot, ApiUtils.MES_SUCCESSFUL_WEEKLYFORECAST),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                new CustomErrorDataResult(ApiUtils.CITY_NAME_ERROR, 400),
                HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/weekly/byLatitudeLongitude")
    public ResponseEntity<DataResult> getWeeklyForecast(
            @RequestParam(name = "lon") float lon,
            @RequestParam(name = "lat") float lat
    ) {
        ForecastRoot forecastRoot = weatherService.getWeeklyForecast(lat, lon);
        if (forecastRoot != null) {
            return new ResponseEntity<>(
                    new SuccessDataResult(forecastRoot, ApiUtils.MES_SUCCESSFUL_WEEKLYFORECAST),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                new ErrorDataResult("Something went wrong."),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @GetMapping("/monthly/byLatitudeLongitude")
    public ResponseEntity<DataResult> getMonthlyWeatherForecastHistory(
            @RequestParam(name = "lon") float lon,
            @RequestParam(name = "lat") float lat) {
        ForecastRoot forecastRoot = weatherService.getMonthlyWeatherForecast(lat + "," + lon);
        if (forecastRoot != null) {
            return new ResponseEntity<>(
                    new SuccessDataResult(forecastRoot, ApiUtils.MES_SUCCESSFUL_MONTHLYFORECAST),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                new ErrorDataResult("Something went wrong."),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @GetMapping("/monthly/byCityName")
    public ResponseEntity<DataResult> getMonthlyWeatherForecast(@RequestParam(name = "cityName") String cityName) {
        if (cityName.matches(ApiUtils.CITY_NAME_REGEX)) {
            ForecastRoot root = weatherService.getMonthlyWeatherForecast(cityName);
            return new ResponseEntity<>(
                    new SuccessDataResult(root, ApiUtils.MES_SUCCESSFUL_MONTHLYFORECAST),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                new CustomErrorDataResult(ApiUtils.CITY_NAME_ERROR, 400),
                HttpStatus.BAD_REQUEST
        );
    }
}
