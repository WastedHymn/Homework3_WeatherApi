package com.patika.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
/*
@JsonIgnoreProperties({
        "date_epoch",
        "astro",
        "hour"
})
*/
public class Forecastday {
    @JsonProperty("date")
    private String date;
    @JsonProperty("day")
    private Day day;
    /*
    @JsonProperty("hour")
    private List<Hour> hour;
    */
    public Forecastday(){
        //this.hour = new ArrayList<>();
    }

}
