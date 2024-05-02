/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import java.util.concurrent.TimeUnit;
import javax.swing.JTextField;

public class Greetings_Service {
    //Клас Greetings_Service відповідає за виведення повідомлення
    // в вікні входу в Outlook

    String youHaveBeenSignedInMessage = "Вхід виконано";
    String greetingsMessage = "Ласкаво просимо";
    String checkMessage = "Перевірте";
    String checkConnectionMessage = "Підключення";
    String checkConnectionToInternetMessage = "До інтернету";

    //Функція відображення текстових строк, розташованих вище(greetingsMessage,
    // checkMessage і т.д.)
    public String greetingShow(String message) {
        return switch (message) {
            case "YouHaveBeenSigned" -> youHaveBeenSignedInMessage;
            case "greetings" -> greetingsMessage;
            case "checkConnection" -> checkConnectionMessage;
            case "checkConnectionToInternet" -> checkConnectionToInternetMessage;
            default -> checkMessage;
        };
    }

    //Виведення повідомлення привітання у разі успішного входу
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
    
    //Виведення повідомлення про відсутність з'єднання у разі відсутності інтернету
    public void connectingError(JTextField field) {
        try {
            field.setText(greetingShow("check"));
            TimeUnit.SECONDS.sleep(2);
            field.setText(greetingShow("checkConnection"));
            TimeUnit.SECONDS.sleep(2);
            field.setText(greetingShow("checkConnectionToInternet"));
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
