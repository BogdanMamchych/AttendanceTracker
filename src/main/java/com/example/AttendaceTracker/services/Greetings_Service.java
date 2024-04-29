/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author boiik
 */
public class Greetings_Service {

    String youHaveBeenSignedInMessage = "Вхід виконано";
    String greetingsMessage = "Ласкаво просимо";
    String checkMessage = "Перевірте";
    String checkConnectingMessage = "Підключення";
    String checkConnectingToInternetMessage = "До інтернету";

    public String greetingShow(String message) {
        if (message.equals("YouHaveBeenSigned")) {
            return youHaveBeenSignedInMessage;
        } else {
            return greetingsMessage;
        }
    }

    public void greeting(JTextField field) {
        try {
            field.setText(greetingShow("YouHaveBeenSigned"));
            TimeUnit.SECONDS.sleep(3);
            field.setText(greetingShow("greetings"));
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
