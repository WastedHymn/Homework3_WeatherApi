package com.patika.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Forecast {
    @JsonProperty("forecastday")
    private List<Forecastday> forecastdays;

    public Forecast() {
        this.forecastdays = new ArrayList<>();
    }

}
