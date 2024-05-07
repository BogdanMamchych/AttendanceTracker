/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker;

import com.example.AttendaceTracker.services.Config_Service;
import com.example.AttendaceTracker.ui.Sign_In_UI;


public class Main {
    //Клас Main - стартер програми
    public static void main(String[] argc) {
        Sign_In_UI startup = new Sign_In_UI();
        //Відкриття головного меню
        startup.setVisible(true);
        //Зчитування конфігурацій з файлу config.properties
        Config_Service.getProperties();
    }
}
