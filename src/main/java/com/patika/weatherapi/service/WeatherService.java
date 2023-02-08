package com.patika.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.weatherapi.model.*;
import com.patika.weatherapi.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    //Convert json response to POJO.
    /*
    private ForecastRoot getForecastRoot(String response) {
        try {
            return objectMapper.readValue(response, ForecastRoot.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    */
    //Get requested information from "Weather Api".
    private ForecastRoot getWeatherInfoFromApi(String requestURL) {
        return restTemplate.getForObject(requestURL, ForecastRoot.class);
    }

    //Get current weather information by city name.
    public ForecastRoot getCurrentWeather(String cityName) {
        String requestURL =
                ApiUtils.WEATHER_API_URL +
                        "q=" + cityName +
                        ApiUtils.AUTH_KEY;
        return getWeatherInfoFromApi(requestURL);
    }

    //Get current weather information by city latitude and longitude.
    public ForecastRoot getCurrentWeather(float lat, float lon) {
        String requestURL =
                ApiUtils.WEATHER_API_URL +
                        "q=" + lat + "," + lon +
                        ApiUtils.AUTH_KEY;
        return getWeatherInfoFromApi(requestURL);
        /*
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()) {
            return responseEntity.getBody();
        }*/
    }

    //Get weekly weather information by city name.
    public ForecastRoot getWeeklyForecast(String cityName) {
        String requestURL =
                ApiUtils.WEATHER_API_URL +
                        "q=" + cityName +
                        "&days=7" +
                        ApiUtils.AUTH_KEY;
        return getWeatherInfoFromApi(requestURL);
    }

    //Get weekly forecast by city latitude and longitude.
    public ForecastRoot getWeeklyForecast(float lat, float lon) {
        String requestURL =
                ApiUtils.WEATHER_API_URL +
                        "q=" + lat + "," + lon +
                        "&days=7" +
                        ApiUtils.AUTH_KEY;
        return getWeatherInfoFromApi(requestURL);
    }

    public ForecastRoot getTwoWeeksForecast(float lat, float lon) {
        String requestURL =
                ApiUtils.WEATHER_API_URL +
                        "q=" + lat + "," + lon +
                        "&days=14" +
                        ApiUtils.AUTH_KEY;
        return getWeatherInfoFromApi(requestURL);
    }

    public ForecastRoot getTwoWeeksForecast(String cityName) {
        String requestURL =
                ApiUtils.WEATHER_API_URL +
                        "q=" + cityName +
                        "&days=14" +
                        ApiUtils.AUTH_KEY;
        return getWeatherInfoFromApi(requestURL);
    }

    public ForecastRoot getWeatherForecast(String cityName, LocalDate date) {
        String query = "&dt=" + ApiUtils.getDateAsString(date);
        String requestURL = ApiUtils.WEATHER_FUTURE_API_URL +
                "q=" + cityName +
                query.trim() +
                ApiUtils.AUTH_KEY;
        return getWeatherInfoFromApi(requestURL);
    }

    public ForecastRoot getMonthlyWeatherForecast(String cityName) {
        System.out.println("getting data..");
        //Get two weeks weather forecast
        ForecastRoot twoWeeksForecast = getTwoWeeksForecast(cityName);
        String date = twoWeeksForecast.getForecast()
                .getForecastdays()
                .get(13)
                .getDate();
        //Split date string
        String[] strList = date.split("-");
        //Create a local date from the last day of the two weeks forecast.
        LocalDate currentDate = LocalDate.of(
                        Integer.parseInt(strList[0]),
                        Integer.parseInt(strList[1]),
                        Integer.parseInt(strList[2])
                )
                .plusDays(1);
        //Get next 16 days' weather forecasts.
        for (int i = 0; i < 16; i++) {
            //Get current date's weather forecast
            ForecastRoot root = getWeatherForecast(cityName, currentDate);
            Condition weatherCondition = root.getForecast()
                    .getForecastdays()
                    .get(0)
                    .getDay()
                    .getCondition();
            //Get weather description text
            String weatherStr = ApiUtils.findWeatherCode(
                    weatherCondition.getCode(), objectMapper
            );
            //Change weather condition text
            weatherCondition
                    .setText(weatherStr);
            //Add weather forecast to twoWeeksForecast
            twoWeeksForecast
                    .getForecast()
                    .getForecastdays()
                    .add(
                            root.getForecast()
                                    .getForecastdays()
                                    .get(0)
                    );
            //Change current date to next day
            currentDate = currentDate.plusDays(1);
        }
        return twoWeeksForecast;
    }
}
