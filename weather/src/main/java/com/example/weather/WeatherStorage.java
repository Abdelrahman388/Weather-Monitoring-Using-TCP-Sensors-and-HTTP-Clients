package com.example.weather;

import java.util.concurrent.ConcurrentHashMap;

public class WeatherStorage {
    private static final ConcurrentHashMap<String, WeatherReading> storage = new ConcurrentHashMap<>();

    public static void updateWeather(String city, WeatherReading reading) {
        storage.put(city.toLowerCase(), reading);
    }

    public static WeatherReading getWeather(String city) {
        return storage.get(city.toLowerCase());
    }
}
