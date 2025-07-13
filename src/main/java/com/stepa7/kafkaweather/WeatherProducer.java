package com.stepa7.kafkaweather;

import com.stepa7.kafkaweather.core.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducer {
    private static final String TOPIC = "weather";

    @Autowired
    private KafkaTemplate<String, WeatherData> kafkaTemplate;

    public void sendWeather(WeatherData weatherData) {
        kafkaTemplate.send(TOPIC, weatherData);
        System.out.println("Отправлено сообщение: " + weatherData.toString());
    }
}
