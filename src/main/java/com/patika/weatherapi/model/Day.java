package com.patika.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties({
        /*
        "memp_c",
        "memp_f",
        "maxwind_mph",
        "maxwind_kph",
        "totalprecip_mm",
        "totalprecip_in",
        "totalsnow_cm",
        "avgvis_km",
        "avgvis_miles",
        "avghumidity",
        "daily_will_it_rain",
        "daily_chance_of_rain",
        "daily_will_it_snow",
        "daily_chance_of_snow",
        "uv",
        */
})
public class Day {
    @JsonProperty("maxtemp_c")
    private double maxtemp_c;
    @JsonProperty("maxtemp_f")
    private double maxtemp_f;
    @JsonProperty("avgtemp_c")
    private double avgtemp_c;
    @JsonProperty("avgtemp_f")
    private double avgtemp_f;
    @JsonProperty("mintemp_c")
    public double mintemp_c;
    @JsonProperty("mintemp_f")
    public double mintemp_f;
    @JsonProperty("condition")
    private Condition condition;

}
