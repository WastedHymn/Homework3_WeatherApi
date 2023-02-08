package com.patika.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
//@JsonIgnoreProperties({"region", "tz_id", "localtime_epoch"})
public class Location{
    private String name;
    private String country;
    private double lat;
    private double lon;
    private String localtime;
}
