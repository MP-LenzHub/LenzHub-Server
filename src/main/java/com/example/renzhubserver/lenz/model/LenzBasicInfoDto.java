package com.example.renzhubserver.lenz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LenzBasicInfoDto {
    private String brightness;
    private String contrast;
    private String backLight;
    private String saturate;
    private String grain;
    private String temperature;
    private String sharpen;
    private String distortion;
}
