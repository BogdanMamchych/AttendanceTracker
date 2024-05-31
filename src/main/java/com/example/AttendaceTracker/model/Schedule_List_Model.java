/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.model;

import java.util.ArrayList;

public class Schedule_List_Model {
    //Клас Schedule_List_Model відповідає за зберігання даних про уроки на сьогодні(1 день)

    //Дані з таблиці ScheduleEvent
    private static ArrayList<ArrayList<Object>> scheduleRows;
    
    //Назви стовбців з таблиці ScheduleEvent 
    private static ArrayList<String> scheduleColumns;

    //Ініціалізація списків для зберігання даних та ствбців з таблиці ScheduleEvent
    public static void initialization() {
        //Якщо список даних порожній
        if (scheduleRows == null) {
            scheduleRows = new ArrayList<>();
        }
        //Якщо список назв стовбців порожній
        if (scheduleColumns == null) {
            scheduleColumns = new ArrayList<>();
        }
    }

    //Функція отримання даних з таблиці ScheduleEvent
    public static ArrayList<ArrayList<Object>> getScheduleRows() {
        return scheduleRows;
    }
    
    //Функція отримання назв стовбців з таблиці ScheduleEvent
    public static ArrayList<String> getScheduleColumns() {
        return scheduleColumns;
    }
    
    //Функція знаходження стовбця
    public static int findColumn(String columnName) {
        for (int i = 0; i < scheduleColumns.size(); i++) {
            //Якщо стовбець знайдено
            if (scheduleColumns.get(i).equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

}
