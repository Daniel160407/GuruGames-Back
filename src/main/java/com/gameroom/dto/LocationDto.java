package com.gameroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private String name;
    private String address;
    private String phone;
    private String hours;
    private String features;
    private double lat;
    private double lng;
}
