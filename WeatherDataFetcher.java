/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.mavenproject1;


import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherDataFetcher {
    private static WeatherDataFetcher instance;
    private final String apiKey = "aa6670dc419248afb87143034241212";
    private final String baseUrl = "https://api.weatherapi.com/v1/current.json";

    private WeatherDataFetcher() {
    }

    public static WeatherDataFetcher getInstance() {
        if (instance == null) {
            instance = new WeatherDataFetcher();
        }
        return instance;
    }

    public JSONObject fetchWeather(String city) {
        HttpClient client = HttpClient.newHttpClient();
        String url = baseUrl + "?key=" + apiKey + "&q=" + city;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
