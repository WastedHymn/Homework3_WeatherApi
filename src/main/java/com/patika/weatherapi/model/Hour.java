package com.patika.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
/*
@JsonIgnoreProperties({
        "time_epoch",
        "is_day",
        "dewpoint_c",
        "dewpoint_f",
        "wind_mph",
        "wind_kph",
        "wind_degree",
        "wind_dir",
        "pressure_mb",
        "pressure_in",
        "precip_mm",
        "precip_in",
        "humidity",
        "cloud",
        "windchill_c",
        "windchill_f",
        "heatindex_c",
        "heatindex_f",
        "dewpo_c",
        "dewpo_f",
        "will_it_rain",
        "chance_of_rain",
        "will_it_snow",
        "chance_of_snow",
        "vis_km",
        "vis_miles",
        "gust_mph",
        "gust_kph",
        "uv",
})
*/
public class Hour {
    @JsonProperty("time")
    private String time;
    @JsonProperty("temp_c")
    private double temp_c;
    @JsonProperty("temp_f")
    private double temp_f;
    @JsonProperty("condition")
    private Condition condition;
    @JsonProperty("feelslike_c")
    private double feelslike_c;
    @JsonProperty("feelslike_f")
    private double feelslike_f;
}
