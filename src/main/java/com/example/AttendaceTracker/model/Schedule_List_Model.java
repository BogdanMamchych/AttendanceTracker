/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.model;

import java.util.ArrayList;

public class Schedule_List_Model {
    //Клас Schedule_List_Model презентує дані з таблиці ScheduleEvent(Урок)

    //Дані з таблиці ScheduleEvent
    private static ArrayList<ArrayList<Object>> scheduleData;
    
    //Назви стовбців з таблиці ScheduleEvent 
    private static ArrayList<String> scheduleColumns;

    public static void initialization() {
        if (scheduleData == null) {
            scheduleData = new ArrayList<>();
        }
        if (scheduleColumns == null) {
            scheduleColumns = new ArrayList<>();
        }
    }

    public static ArrayList<ArrayList<Object>> getScheduleData() {
        return scheduleData;
    }
    
    public static ArrayList<String> getScheduleColumns() {
        return scheduleColumns;
    }
    
    public static int findColumn(String columnName) {
        for (int i = 0; i < scheduleColumns.size(); i++) {
            if (scheduleColumns.get(i).equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

}
