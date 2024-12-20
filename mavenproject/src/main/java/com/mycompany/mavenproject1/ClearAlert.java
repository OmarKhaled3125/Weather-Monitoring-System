/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author كمبيولاب
 */
public class ClearAlert implements WeatherAlert {
    @Override
    public void generateAlert() {
        System.out.println("The weather is clear today");
    }
}
