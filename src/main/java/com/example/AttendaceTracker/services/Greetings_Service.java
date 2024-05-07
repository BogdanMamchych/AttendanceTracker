/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import com.example.AttendaceTracker.ui.Messages_UI;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextField;

public class Greetings_Service{
    //Клас Greetings_Service відповідає за виведення повідомлення
    // в вікні входу в Outlook

    String youHaveBeenSignedInMessage = "Вхід виконано";
    String greetingsMessage = "Ласкаво просимо";


    //Функція відображення текстових строк, розташованих вище(greetingsMessage,
    // checkMessage і т.д.)
    private String greetingShow(String message) {
        if(message.equals("YouHaveBeenSigned")) { 
            return youHaveBeenSignedInMessage;
        } else {
            return greetingsMessage;
        }
    }

    //Виведення повідомлення привітання у разі успішного входу
    
    public void greeting(JTextField field) {
        try {
            field.setText(greetingShow("YouHaveBeenSigned"));
            TimeUnit.SECONDS.sleep(2);
            field.setText(greetingShow("greetings"));
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Messages_UI.showErrorMessage(e.toString());
        }
    }

}
