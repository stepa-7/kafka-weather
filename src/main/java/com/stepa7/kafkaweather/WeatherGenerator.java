package com.stepa7.kafkaweather;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class WeatherGenerator {
    private final Random random = new Random();
    private final String[] statuses = {"Солнечно", "Дождь", "Облачно"};
    private final String[] cities = {"Магадан", "Чукотка", "Питер", "Тюмень", "Москва", "Нижний Новгород"};

    WeatherData generateWeatherData() {
        int temperature = random.nextInt(36);
        String status = statuses[random.nextInt(statuses.length)];
        String city = cities[random.nextInt(cities.length)];
        LocalDateTime date = generateTime();
        return new WeatherData(temperature, status, city, date);
    }

    private LocalDateTime generateTime() {
        int days = random.nextInt(7);
        int hours = random.nextInt(25);
        int minutes = random.nextInt(61);
        return LocalDateTime.now().minusDays(days).minusHours(hours).minusMinutes(minutes);
    }
}
