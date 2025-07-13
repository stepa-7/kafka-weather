package com.stepa7.kafkaweather;

import com.stepa7.kafkaweather.core.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherProducerTest {
    @Mock
    KafkaTemplate<String, WeatherData> kafkaTemplate;

    @InjectMocks
    WeatherProducer weatherProducer;

    @Test
    public void testWeatherProducer() {
        WeatherData weatherData = new WeatherData(13, "Солнечно", "Москва", LocalDateTime.now());
        weatherProducer.sendWeather(weatherData);
        verify(kafkaTemplate, times(1)).send("weather", weatherData);
    }
}
