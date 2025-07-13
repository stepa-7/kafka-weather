package com.stepa7.kafkaweather;

import com.stepa7.kafkaweather.core.WeatherData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherDataTest {
    @Test
    public void testWeatherData() {
        LocalDateTime now = LocalDateTime.now();
        WeatherData weatherData = new WeatherData(13, "Солнечно", "Москва", now);
        assertTrue(weatherData.getTemperature() == 13);
        assertTrue(weatherData.getStatus().equals("Солнечно"));
        assertTrue(weatherData.getCity().equals("Москва"));
        assertTrue(weatherData.getDate().equals(now));
    }
}
