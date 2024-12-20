package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONObject;

public class WeatherAppGUI {
    private JFrame frame;
    private JTextField cityField;
    private JComboBox<String> tempUnitCombo;
    private JComboBox<String> windUnitCombo;
    private JTextArea resultArea;

    public WeatherAppGUI() {
        frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 650); 
        frame.getContentPane().setBackground(new Color(240, 248, 255)); 
        frame.setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); 

        // City input
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        JLabel cityLabel = new JLabel("Enter City:");
        cityLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        frame.add(cityLabel, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 1; 
        gbc.gridwidth = 2; 
        cityField = new JTextField(20);
        cityField.setBackground(Color.WHITE);
        cityField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cityField.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));
        frame.add(cityField, gbc);

        // Temperature unit selection
        gbc.gridx = 0; 
        gbc.gridy = 2; 
        gbc.gridwidth = 1; 
        JLabel tempLabel = new JLabel("Temperature Unit:");
        tempLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); 
        frame.add(tempLabel, gbc);

        gbc.gridx = 1; 
        gbc.gridy = 2; 
        tempUnitCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        tempUnitCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        tempUnitCombo.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));
        frame.add(tempUnitCombo, gbc);

        // Wind speed unit selection
        gbc.gridx = 0; 
        gbc.gridy = 3; 
        JLabel windLabel = new JLabel("Wind Speed Unit:");
        windLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); 
        frame.add(windLabel, gbc);

        gbc.gridx = 1; 
        gbc.gridy = 3; 
        windUnitCombo = new JComboBox<>(new String[]{"Kph", "Mph"});
        windUnitCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        windUnitCombo.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));
        frame.add(windUnitCombo, gbc);

        // Fetch weather button
        gbc.gridx = 0;
        gbc.gridy = 4; 
        gbc.gridwidth = 2; 
        JButton fetchButton = new JButton("Fetch Weather");
        fetchButton.setBackground(new Color(70, 130, 180)); 
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setFont(new Font("Segoe UI", Font.BOLD, 20)); 
        fetchButton.setPreferredSize(new Dimension(200, 50)); 
        fetchButton.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));
        frame.add(fetchButton, gbc);

        // Result area
        gbc.gridx = 0; 
        gbc.gridy = 5; 
        gbc.gridwidth = 2; 
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setBackground(Color.WHITE);
        resultArea.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        frame.add(new JScrollPane(resultArea), gbc);

        // Button action
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchWeather();
            }
        });

        frame.setVisible(true);
    }

    private void fetchWeather() {
        String city = cityField.getText();
        String tempUnit = (String) tempUnitCombo.getSelectedItem();
        String windUnit = (String) windUnitCombo.getSelectedItem();

        UserPreferencesManager preferences = UserPreferencesManager.getInstance();
        preferences.setTemperatureUnit(tempUnit);
        preferences.setWindSpeedUnit(windUnit);

        WeatherDataFetcher fetcher = WeatherDataFetcher.getInstance();
        JSONObject weatherData = fetcher.fetchWeather(city);

        if (weatherData != null) {
            double temperature = weatherData.getJSONObject("current").getDouble(tempUnit.equals("Celsius") ? "temp_c" : "temp_f");
            double windspeed = weatherData.getJSONObject("current").getDouble(windUnit.equals("Kph") ? "wind_kph" : "wind_mph");
            double humidity = weatherData.getJSONObject("current").getDouble("humidity");

            // Fetch the weather condition
            String condition = weatherData.getJSONObject("current").getJSONObject("condition").getString("text");

            // Prepare the result message
            StringBuilder resultMessage = new StringBuilder();
            resultMessage.append("Temperature: ").append(temperature).append("\n")
                         .append("Wind Speed: ").append(windspeed).append("\n")
                         .append("Humidity: ").append(humidity).append("\n");

            // Check for clear weather condition
            if (condition.equalsIgnoreCase("Clear")) {
                resultMessage.append("The weather is clear today.");
            }

            resultArea.setText(resultMessage.toString());
        } else {
            resultArea.setText("Failed to fetch weather data. Please check the city name or try again.");
        }
    }

    public static void main(String[] args) {
        new WeatherAppGUI();
    }
}