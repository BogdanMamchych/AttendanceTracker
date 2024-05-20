/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import com.example.AttendaceTracker.dto.Subject_List_DTO;
import com.example.AttendaceTracker.model.Subject_List_Model;
import com.example.AttendaceTracker.model.User_Model;
import java.util.ArrayList;


public class Subject_Service {
    //Клас Subject_Service відповідає за дії над списком предметів
    
    //Функція отримання даних предметів
    public static String receive(ArrayList<String> parameters, ArrayList<Object> values) {
        //Отримання даних предметів
        String result = Database_Service.retreiveDataByParameters(parameters, values, "*", "Subject");
        //Якщо дані отримано успішно
        if (result.equals("successfull")) {
            //Вилучення отриманих даних
            String result_getData = Database_Service.getData(Subject_List_Model.getSubjectDataList());
            //Вилучення назв стовбців таблиці дисципліни
            String result_getColumn = Database_Service.getColumns(Subject_List_Model.getSubjectColumnsList());
            //Якшо дані вилучені успішно
            if (result_getData.equals("successfull") && result_getColumn.equals("successfull")) {
                return "successfull";
            }
        }
        return result;
    }
    
    //Функція-стартер функції отримання даних предметів
    public static String recieveSubjectData() {
        //Формування списку назв параметрів
        ArrayList<String> parameters = new ArrayList();
        parameters.add("instructor_ID");
        //Формування списку параметрів
        ArrayList<Object> values = new ArrayList();
        values.add(User_Model.getId());
        //Виконання функції отримання даних предметів
        return receive(parameters, values);
    }
    
    //Конвертація даних предметів в DTO для подальшого використання
    public static void toDTO() {
        ArrayList<String> subjects = new ArrayList<>();
        //Отримання даних зі списку предметів
        for(ArrayList<Object> rowData : Subject_List_Model.getSubjectDataList()) {
            String subject = (String) rowData.get(Subject_List_Model.findColumn("name"));
            subjects.add(subject);
        }
        //Внесення даних в DTO
        Subject_List_DTO.setList(subjects);
    }
    
    //Функція ініціалізації списків в класі Subject_List_Model
    public static void init() {
        Subject_List_Model.init();
    }
}
