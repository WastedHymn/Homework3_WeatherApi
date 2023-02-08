package com.patika.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//@JsonIgnoreProperties({"icon"})
public class Condition{
    @JsonProperty("text")
    private String text;
    @JsonProperty("code")
    private Integer code;
}
