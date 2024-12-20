/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import java.util.Random;
import org.json.JSONObject;
import java.util.Scanner;


public class Mavenproject1 {

    
    public static void main(String[] args) {
        // Scanner to take city input from user
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the city: ");
        String city = scanner.nextLine();

        UserPreferencesManager preferences = UserPreferencesManager.getInstance();
        
        System.out.print("Enter the type of Temperature(Celsius / Fahrenheit) : ");
        preferences.setTemperatureUnit(scanner.nextLine());
        
        String temp_type = "";
        if (preferences.getTemperatureUnit().equals("Celsius")) {
            temp_type = "temp_c";
        } else if (preferences.getTemperatureUnit().equals("Fahrenheit")) {
            temp_type = "temp_f";
        }
        
        System.out.print("Enter the type of Wind Speed(Kph / Mph) : ");
        preferences.setWindSpeedUnit(scanner.nextLine());
        
        String wind_type = "";
        if (preferences.getWindSpeedUnit().equals("Kph")) {
            wind_type = "wind_kph";
        } else if (preferences.getWindSpeedUnit().equals("Mph")) {
            wind_type = "wind_mph";
        }
        
        // Close the scanner
        scanner.close();
        
        // Singleton for WeatherDataFetcher
        WeatherDataFetcher fetcher = WeatherDataFetcher.getInstance();
        JSONObject weatherData = fetcher.fetchWeather(city);

        if (weatherData != null) {
    

            // Display temperature data
           
            double temperature = weatherData.getJSONObject("current").getDouble(temp_type);
            WeatherData tempData = WeatherDataFactory.createWeatherData("temperature", temperature);
            tempData.display();
            
            double windspeed = weatherData.getJSONObject("current").getDouble(wind_type);
            WeatherData windData = WeatherDataFactory.createWeatherData("windspeed", windspeed);
            windData.display();
            
             double humidity = weatherData.getJSONObject("current").getDouble("humidity");
            WeatherData humData = WeatherDataFactory.createWeatherData("humidity", humidity);
            humData.display();
            
            
//            
//             Random random = new Random();
//        int randomNumber = random.nextInt((2 - 0) + 1) + 0;
     
            String condition = weatherData.getJSONObject("current").getJSONObject("condition").getString("text");
           // System.out.println(condition);
           WeatherAlert alter = WeatherAlertFactory.getWeatherAlert(condition);
           alter.generateAlert();
            
        } else {
            System.out.println("Failed to fetch weather data. Please check the city name or try again.");
        }

   
       
    }
}

    
