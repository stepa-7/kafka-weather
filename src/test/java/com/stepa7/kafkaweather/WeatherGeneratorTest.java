package com.stepa7.kafkaweather;

import com.stepa7.kafkaweather.core.WeatherData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherGeneratorTest {
    @Test
    public void testWeatherGenerator() {
        WeatherGenerator weatherGenerator = new WeatherGenerator();
        WeatherData weatherData = weatherGenerator.generateWeatherData();
        assertNotNull(weatherData);
        assertNotNull(weatherData.getCity());
        assertNotNull(weatherData.getDate());
        assertNotNull(weatherData.getStatus());
        assertTrue(weatherData.getTemperature() >= 0 && weatherData.getTemperature() <= 35);
    }
}
