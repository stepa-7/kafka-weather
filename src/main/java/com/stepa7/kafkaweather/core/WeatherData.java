package com.stepa7.kafkaweather.core;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WeatherData {
    private int temperature;
    private String status;
    private String city;
    private LocalDateTime date;
}