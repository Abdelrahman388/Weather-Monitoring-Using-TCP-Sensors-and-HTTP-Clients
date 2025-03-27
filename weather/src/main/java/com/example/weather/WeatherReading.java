package com.example.weather;


import java.time.LocalDateTime;

public class WeatherReading {
    private String city;
    private double temperature;
    private double humidity;
    private LocalDateTime timestamp;

    public WeatherReading() {}

    public WeatherReading(String city, double temperature, double humidity, LocalDateTime timestamp) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    
    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
