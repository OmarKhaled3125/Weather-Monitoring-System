/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author كمبيولاب
 */

public class WeatherAlertFactory {
    
    
    
     public static WeatherAlert getWeatherAlert(String condition) {
        if (condition == null) {
            throw new IllegalArgumentException("Condition cannot be null");
        }

        switch (condition.toLowerCase()) {
            case "Rain":
                return new RainAlert();
            case "Snow":
                return new SnowAlert();
            default:
                 return new ClearAlert();
        }
    }
}

