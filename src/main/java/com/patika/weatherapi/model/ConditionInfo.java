package com.patika.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//@JsonIgnoreProperties({"icon", "night"})
public class ConditionInfo{
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("day")
    private String text;
}