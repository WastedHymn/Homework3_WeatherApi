package com.patika.weatherapi.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.weatherapi.model.ConditionInfo;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtils {
    public static final String WEATHER_API_URL = "http://api.weatherapi.com/v1/forecast.json?";
    public static final String WEATHER_FUTURE_API_URL = "http://api.weatherapi.com/v1/future.json?";
    public static final String AUTH_KEY = "&key=ef6795061fd94b61a7820245230502";
    public static final String CITY_NAME_ERROR = "A city name can only contain letters and '-'.";
    public static final String CITY_NAME_REGEX =
            "[a-zA-ZàèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœğĞşŞ\\s-]+$";

    private static Map<Integer, String> weatherCodeMap;

    private static void readWeatherInfoJsonFile(ObjectMapper objectMapper) {
        File jsonFile;
        try {
            jsonFile = new ClassPathResource("static/weather_codes.json").getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            List<ConditionInfo> weatherCodes;
            weatherCodes = objectMapper.readValue(
                    jsonFile,
                    new TypeReference<>() {
                    }
            );
            weatherCodeMap = new HashMap<>();
            for (ConditionInfo weatherCode : weatherCodes) {
                weatherCodeMap.put(weatherCode.getCode(), weatherCode.getText());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findWeatherCode(int codeNumber, ObjectMapper objectMapper) {
        if (weatherCodeMap == null)
            readWeatherInfoJsonFile(objectMapper);
        return weatherCodeMap.get(codeNumber);
    }

    public static String getDateAsString(LocalDate date) {
        String day = "" + date.getDayOfMonth();
        String month = "" + date.getMonthValue();
        if (date.getMonthValue() < 10)
            month = "0" + month;

        if (date.getDayOfMonth() < 10)
            day = "0" + day;

        return date.getYear() + "-" + month + "-" + day;
    }
}
