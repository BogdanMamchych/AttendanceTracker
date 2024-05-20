/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.dto;

import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author boiik
 */
public class Subject_List_DTO {
    //Клас Subject_List_DTO відповідає за зберігання та передачу даних про предмети
    // між різними частинами програми.
    
    //Список назв предметів
    private static ArrayList<String> SubjectList;
    
    //Функція отримання назв предметів
    public static void setList(ArrayList<String> list) {
        SubjectList = new ArrayList<>(list);
    }
    
    //Функція переміщення списку назв предметів в інтерфейс програми(в випадаючий список)
    public static void sendToComboBox(JComboBox combobox) {
      for (String subject : SubjectList) {
          combobox.addItem(subject);
      }
    }
}
