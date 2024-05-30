/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.model;

public class Instructor_Model {
    //Клас Instructor_Model презентує викладача
    
    //Єдиний екземпляр класу
    private static Instructor_Model instance;
    //Ім'я викладача
    private String name; 
    //Прізвище викладача
    private String surname;                                              
    //Електронна пошта викладача
    private String email;    
    //Ідентифікаційний номер викладача
    private int id;
    
    private Instructor_Model() {
        // Приватний конструктор, щоб заборонити створення екземплярів класу ззовні
    }
    
    //Функція отримання єдиного екземпляру
    public static Instructor_Model getInstance() {
        //Якщо екземпляру немає
        if (instance == null) {
            instance = new Instructor_Model();
        }
        return instance;
    }

    //Функція отримання імені викладача
    public String getName() {
        return name;
    }

    //Функція встановлення імені викладача
    public void setName(String name) {
        this.name = name;
    }

    //Функція отримання прізвища викладача
    public String getSurname() {
        return surname;
    }

    //Функція встановлення прізвища викладача
    public void setSurname(String surname) {
        this.surname = surname;
    }

    //Функція отримання електронної пошти викладача
    public String getEmail() {
        return email;
    }

    //Функція встановлення електронної пошти викладача
    public void setEmail(String email) {
        this.email = email;
    }

    //Функція отримання ідентифікаційного номеру викладача
    public int getId() {
        return id;
    }

    //Функція встановлення ідентифікаційного номеру викладача
    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
