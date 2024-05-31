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
public class Subject_Model {
    //Клас Subject_Model відповідає за зберігання списку назв дисциплін
    // для подальшого використання протягом виконання програми

    //Єдиний екземпляр класу
    private static Subject_Model instance;
    //Список назв дисциплін
    private ArrayList<String> subjects;
    
    private Subject_Model() {
        //Порожній клас, щоб заборонити створення екземплярів класу ззовні
    }

    //Функція отримання єдиного екземпляру
    public static synchronized Subject_Model getInstance() {
        //Якщо екземпляру немає
        if (instance == null) {
            instance = new Subject_Model();
        }
        return instance;
    }

    //Функція отримання списку назв дисциплін
    public ArrayList<String> getSubjects() {
        return subjects;
    }


}

