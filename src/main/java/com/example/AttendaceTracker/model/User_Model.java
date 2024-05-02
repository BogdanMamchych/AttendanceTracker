/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.model;

public class User_Model {
    //Клас User_Model презентує користувача(в даний момент - викладача)
    
    //Ім'я користувача
    private static String name; 
    
    //Прізвище користувача
    private static String surname;                                              
    
    //Електронна пошта користувача
    private static String email;                                                

    //Функція отримання імені користувача
    public static String getName() {
        return name;
    }

    //Функція встановлення імені користувача
    public static void setName(String name) {
        User_Model.name = name;
    }

    //Функція отримання прізвища користувача
    public static String getSurname() {
        return surname;
    }

    //Функція встановлення прізвища користувача
    public static void setSurname(String surname) {
        User_Model.surname = surname;
    }

    //Функція отримання електронної пошти користувача
    public static String getEmail() {
        return email;
    }

    //Функція встановлення електронної пошти користувача
    public static void setEmail(String email) {
        User_Model.email = email;
    }
    
    
}
