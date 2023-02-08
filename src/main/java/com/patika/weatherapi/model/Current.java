package com.patika.weatherapi.model;

import lombok.Data;

@Data
/*
@JsonIgnoreProperties({

        "last_updated_epoch",
        "is_day",
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
        "vis_km",
        "vis_miles",
        "uv",
        "gust_mph",
        "gust_kph",

})
*/
public class Current{
    private String last_updated;
    private double temp_c;
    private double temp_f;
    private double feelslike_c;
    private double feelslike_f;
    private Condition condition;
}
