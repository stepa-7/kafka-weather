package com.stepa7.kafkaweather;

import com.stepa7.kafkaweather.core.WeatherData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducerScheduler {
    private final WeatherProducer weatherProducer;
    private final WeatherGenerator weatherGenerator;

    public WeatherProducerScheduler(WeatherProducer weatherProducer, WeatherGenerator weatherGenerator) {
        this.weatherProducer = weatherProducer;
        this.weatherGenerator = weatherGenerator;
    }

    @Scheduled(fixedRate = 5000)
    public void sendWeatherData() {
        WeatherData weatherData = weatherGenerator.generateWeatherData();
        weatherProducer.sendWeather(weatherData);
    }
}
