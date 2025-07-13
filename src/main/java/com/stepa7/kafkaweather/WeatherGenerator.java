package com.stepa7.kafkaweather;

import com.stepa7.kafkaweather.core.WeatherConstants;
import com.stepa7.kafkaweather.core.WeatherData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class WeatherGenerator {
    private final Random random = new Random();

    public WeatherData generateWeatherData() {
        int temperature = random.nextInt(36);
        String status = WeatherConstants.STATUSES[random.nextInt(WeatherConstants.STATUSES.length)];
        String city = WeatherConstants.CITIES[random.nextInt(WeatherConstants.CITIES.length)];
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
