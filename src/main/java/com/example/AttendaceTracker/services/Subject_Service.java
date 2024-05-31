/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import com.example.AttendaceTracker.repository.Database_Repository;
import com.example.AttendaceTracker.dto.Subject_DTO;
import com.example.AttendaceTracker.model.Instructor_Model;
import com.example.AttendaceTracker.model.Subject_Model;
import java.util.ArrayList;

public class Subject_Service {
    //Клас Subject_Service відповідає за дії над списком предметів з бази даних

    //Посилання на модель предметів
    private Subject_Model subjectModel = Subject_Model.getInstance();
    //Дані з таблиці Subject
    private ArrayList<ArrayList<Object>> subjectRows;
    //Стовбці з таблиці Subject
    private ArrayList<String> subjectColumns;
   
    // Функція отримання даних предметів
    private String receive(ArrayList<String> parameters, ArrayList<Object> values) {
        // Отримання даних предметів
        String result = Database_Repository.retrieveDataByParameters(parameters, values, "*", "Subject",
                subjectRows, subjectColumns);
        // Якщо дані отримано успішно
        if (result.equals("successfull")) {
            return "successfull";
        }
        return result;
    }
    
    public boolean isEmpty() {
        return subjectModel.getSubjects().isEmpty();
    }

    // Функція-стартер функції отримання даних предметів
    public String fetchSubjectData() {
        // Формування списку назв параметрів
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("instructor_ID");
        // Формування списку параметрів
        ArrayList<Object> values = new ArrayList<>();
        Instructor_Model model = Instructor_Model.getInstance();
        values.add(model.getId());
        // Виконання функції отримання даних предметів
        return receive(parameters, values);
    }

    // Конвертація даних предметів в model для Зберігання
    public void toModel() {
        subjectModel = Subject_Model.getInstance();
        // Отримання даних зі списку предметів
        for (ArrayList<Object> rowData : subjectRows) {
            String subject = (String) rowData.get(findColumn("name"));
            subjectModel.getSubjects().add(subject);
        }
    }
    
    //Конвертація даних предметів в DTO для подальшого використання
    public Subject_DTO toDTO() {
        Subject_DTO dto = new Subject_DTO();
        dto.setList(subjectModel.getSubjects());
        return dto;
    }

    // Функція пошуку стовбця
    public int findColumn(String columnName) {
        for (int i = 0; i < subjectColumns.size(); i++) {
            // Якщо даний стовбець є в списку
            if (subjectColumns.get(i).equals(columnName)) {
                return i;
            }
        }
        return -1;
    }
}
