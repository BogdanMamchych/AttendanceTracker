/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.ui;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Messages_UI{
    //Клас Messages_UI відповідає за виведення на екран різних повідомлень(помилка, успіх і т.д.);
    
    //Функція виведення повідомлення про помилку
    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Помилка", JOptionPane.ERROR_MESSAGE);
    }
    
}
