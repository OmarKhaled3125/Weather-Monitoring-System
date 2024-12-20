/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author كمبيولاب
 */
public class SnowAlert implements WeatherAlert {
    @Override
    public void generateAlert() {
        System.out.println("Snow Alert: Snowstorm incoming. Drive carefully!");
    }
}
