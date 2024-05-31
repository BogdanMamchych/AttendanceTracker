/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.model;

import java.util.ArrayList;

/**
 *
 * @author boiik
 */
public class Group_Model {
    //Клас Group_Model відповідає за зберігання списку номерів груп
    // для подальшого використання протягом виконання програми
    
    //Єдиний екземпляр класу
    private static Group_Model instance;
    //Список номерів груп
    private ArrayList<String> groups;
    
    public Group_Model() {
        //Порожній клас, щоб заборонити створення екземплярів класу ззовні
    }
    
    //Функція отримання єдиного екземпляру
    public static synchronized Group_Model getInstance() {
        //Якщо екземпляру немає
        if (instance == null) {
            instance = new Group_Model();
        }
        return instance;
    }

    //Функція отримання списку номерів груп
    public ArrayList<String> getSubjects() {
        return groups;
    }
 
    
}
