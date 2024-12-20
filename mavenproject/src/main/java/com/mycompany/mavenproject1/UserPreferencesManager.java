/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.mavenproject1;

/**
 *
 * @author كمبيولاب
 */

public class UserPreferencesManager {
    private static UserPreferencesManager instance;
    private String temperatureUnit = "Celsius";
    private String windSpeedUnit = "km/h";

    private UserPreferencesManager() {}

    public static UserPreferencesManager getInstance() {
        if (instance == null) {
            instance = new UserPreferencesManager();
        }
        return instance;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String unit) {
        if (unit.equals("Celsius") || unit.equals("Fahrenheit")) {
            this.temperatureUnit = unit;
        } else {
            System.out.println("Invalid temperature unit. Keeping the previous value: " + this.temperatureUnit);
        }
    }

    public String getWindSpeedUnit() {
        return windSpeedUnit;
    }

    public void setWindSpeedUnit(String unit) {
        if (unit.equals("Kph") || unit.equals("Mph")) {
            this.windSpeedUnit = unit;
        } else {
            System.out.println("Invalid wind speed unit. Keeping the previous value: " + this.windSpeedUnit);
        }
    }
}