/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.ui;

import javax.swing.*;

public class Messages_UI extends JFrame{
    //Клас Messages_UI відповідає за виведення на екран різних повідомлень(помилка, успіх і т.д.);
    
    //Функція виведення повідомлення про помилку
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Помилка", JOptionPane.ERROR_MESSAGE);
    }
    
}
