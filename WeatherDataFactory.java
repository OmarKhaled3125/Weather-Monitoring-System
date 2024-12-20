/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author كمبيولاب
 */

public class WeatherDataFactory {
    public static WeatherData createWeatherData(String type, double value) {
        switch (type.toLowerCase()) {
            case "temperature":
                return new TemperatureData(value);
            case "humidity":
                return new HumidityData(value);
            case "windspeed":
                return new WindSpeedData(value);
            default:
                throw new IllegalArgumentException("Unknown weather data type: " + type);
        }
    }
}
