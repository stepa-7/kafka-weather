package com.stepa7.kafkaweather;

import com.stepa7.kafkaweather.core.WeatherConstants;
import com.stepa7.kafkaweather.core.WeatherData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WeatherConsumer {
    private static final String TOPIC = "weather";
    private final List<WeatherData> weatherDataList = new ArrayList<>();

    @KafkaListener(topics = TOPIC)
    public void consume(WeatherData weatherData) {
        weatherDataList.add(weatherData);
        System.out.println("Получено сообщение: " + weatherData.toString());
    }

    @Scheduled(fixedRate = 30000)
    public void analyzeData() {
        dataWithMaxTemperature();
        dataWithMinTemperature();
        avgTemperature();
        avgTemperatureByCities();
        countStatusByCities();
    }


    private void dataWithMaxTemperature() {
        System.out.println();
        weatherDataList.stream().max(Comparator.comparingInt(WeatherData::getTemperature))
                .ifPresent(weatherData -> System.out.println("Самая высокая температура была в городе " + weatherData.getCity() +
                        " с значением " + weatherData.getTemperature() + " градусов днём " + weatherData.getDate()));
    }

    private void dataWithMinTemperature() {
        weatherDataList.stream().min(Comparator.comparingInt(WeatherData::getTemperature))
                .ifPresent(weatherData -> System.out.println("Самая низкая температура была в городе " + weatherData.getCity() +
                        " с значением " + weatherData.getTemperature() + " градусов днём " + weatherData.getDate()));
    }

    private void avgTemperature() {
        Double average = weatherDataList.stream()
                .mapToInt(WeatherData::getTemperature)
                .average().orElse(Double.NaN);
        System.out.println("Средняя температура по всем городам = " + average);
    }

    private void avgTemperatureByCities() {
        System.out.printf("Средняя температура по городам: ");
        for (String city : WeatherConstants.CITIES) {
            Double average = weatherDataList.stream()
                    .filter(weatherData -> weatherData.getCity().equals(city))
                    .mapToInt(WeatherData::getTemperature)
                    .average().orElse(Double.NaN);
            if (!average.isNaN()) System.out.printf(city + " = " + average + ", ");
        }
        System.out.print("\n");
    }

    private void countStatusByCities() {
        for (String status : WeatherConstants.STATUSES) {
            long maxCount = -1;
            List<String> resultCities = new ArrayList<>();
            for (String city : WeatherConstants.CITIES) {
                long count = weatherDataList.stream()
                    .filter(weatherData -> weatherData.getCity().equals(city))
                    .filter(weatherData -> weatherData.getStatus().equals(status))
                    .count();
                if (count > maxCount) {
                    maxCount = count;
                    resultCities.clear();
                    resultCities.add(city);
                } else if (count == maxCount) {
                    resultCities.add(city);
                }
            }
            if (resultCities.size() == 1) {
                System.out.println("Самое большое количество дней с погодой " + status + " было в городе " + resultCities.get(0) + " = " + maxCount);
            } else {
                System.out.printf("Самое большое количество дней с погодой " + status + " было в городах ");
                for (String city : resultCities) {
                    System.out.printf(city + ", ");
                }
                System.out.printf(" = " + maxCount + "\n");
            }
        }
        System.out.print("\n");
    }
}
