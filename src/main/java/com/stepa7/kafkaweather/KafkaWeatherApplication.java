package com.stepa7.kafkaweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

@EnableScheduling
@SpringBootApplication
public class KafkaWeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaWeatherApplication.class, args);
    }

}
