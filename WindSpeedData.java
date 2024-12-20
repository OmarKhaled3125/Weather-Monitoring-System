/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author كمبيولاب
 */
class WindSpeedData implements WeatherData {
    private final double value;
    
     UserPreferencesManager preferences = UserPreferencesManager.getInstance();

    public WindSpeedData(double value) {
        this.value = value;
    }

    public void display() {
        System.out.println("Wind Speed: " + value + " " +preferences.getWindSpeedUnit());
    }
}
