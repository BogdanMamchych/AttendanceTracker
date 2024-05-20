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
public class Subject_List_Model {
    //Даний клас презентує дані з класу Subject(Предмет)

    //Дані з таблиці Subject
    private static ArrayList<ArrayList<Object>> subjectData;
    
    //Назви стовбців з таблиці Subject
    private static ArrayList<String> subjectColumns;

    //Ініціалізація списків даних та назв стовбців
    public static void init() {
        //Якщо список даних не ініціалізовано
        if (subjectData == null) {
            subjectData = new ArrayList();
        }
        //Якщо список назв стовбців не ініціалізовано
        if (subjectColumns == null) {
            subjectColumns = new ArrayList();
        }
    }

    //Функція-геттер списку даних з таблиці
    public static ArrayList<ArrayList<Object>> getSubjectDataList() {
        return subjectData;
    }

    //Функція-геттер списку назв стовбців з таблиці
    public static ArrayList<String> getSubjectColumnsList() {
        return subjectColumns;
    }

    //Функція пошуку стовбця
    public static int findColumn(String columnName) {
        for (int i = 0; i < subjectColumns.size(); i++) {
            //Якщо даний стовбець є в списку
            if (subjectColumns.get(i).equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

}
