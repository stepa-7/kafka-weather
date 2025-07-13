package com.stepa7.kafkaweather;

import com.stepa7.kafkaweather.core.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class WeatherProducerSchedulerTest {
    @Mock
    WeatherProducer mockProducer;
    @Mock
    WeatherGenerator mockGenerator;
    @InjectMocks
    WeatherProducerScheduler scheduler;

    @Test
    public void testSendWeatherData() {
        WeatherData weatherData = new WeatherData(13, "Солнечно", "Москва", LocalDateTime.now());
        when(mockGenerator.generateWeatherData()).thenReturn(weatherData);
        scheduler.sendWeatherData();
        verify(mockGenerator, times(1)).generateWeatherData();
        verify(mockProducer, times(1)).sendWeather(weatherData);
    }
}
