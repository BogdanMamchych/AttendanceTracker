/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.dto;

public class Discipline_DTO {
    //Клас Discipline_DTO відповідає за транспортування назви дисципліни
    
    private static String discipline;                                           //Назва дисципліни
    
    public Discipline_DTO(String discipline) {
        Discipline_DTO.discipline = discipline;
    }
    
    //Функція для отримання назви дисципліни
    public String getDiscipline() {
        return discipline;
    }
    
}
