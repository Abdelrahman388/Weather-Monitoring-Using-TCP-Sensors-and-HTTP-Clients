package com.example.weather;

import java.net.URLDecoder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherRestController {

    @GetMapping("/weather/{city}")
    public ResponseEntity<?> getWeather(@PathVariable String city) {
        WeatherReading reading = WeatherStorage.getWeather(URLDecoder.decode(city));
        if (reading != null) {
            return ResponseEntity.ok(reading);
        } else {
            return ResponseEntity.status(404).body("{\"message\": \"No data available\"}");
        }
    }
}
