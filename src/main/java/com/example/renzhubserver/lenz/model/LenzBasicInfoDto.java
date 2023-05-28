package com.example.renzhubserver.lenz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LenzBasicInfoDto {
    private Float brightness;
    private Float contrast;
    private Float backLight;
    private Float saturate;
    private Float grain;
    private Float temperature;
    private Float sharpen;
    private Float distortion;
}
