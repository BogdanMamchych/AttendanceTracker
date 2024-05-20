/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import com.example.AttendaceTracker.model.Schedule_List_Model;
import com.example.AttendaceTracker.model.User_Model;
import com.example.AttendaceTracker.ui.Messages_UI;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class Schedule_Service {

    //Дані для відображення на таблицю розкладу
    private static ArrayList<ArrayList<Object>> dataForTable;
    
    //Стовбці таблиці розкладу
    private static final String[] columns = {"Дисципліна", "Подія", "Початок", "Закінчення"};

    //Функція заповнення таблиці
    //Етапи заповнення таблиці:
    //1. Отримання моделі таблиці table як DefaultTableModel для маніпуляції даними в таблиці розкладу
    //2. Очищення стовбців таблиці
    //3. Якщо таблиця не містить стовбців, додавання стовбців до таблиці розкладу
    //4. Налаштування ширини деяких стовбців
    //5. Формування та додавання даних до таблиці розкладу
    //5.1. Отримання години та хвилини закічення уроку з рядків
    //5.2. Якщо поточний час раніше, ніж час закінчення даного уроку:
    //5.2.1. Отримання дисципліни
    //5.2.1.1. Якщо вдалося отримати рядок предмета за ідентифікаційним кодом
    //5.2.1.1.1. Зчитування інших даних з рядків
    //5.2.1.1.2. Формування стовбця для таблиці розкладу з даних
    //5.2.1.1.3. Добавлення стовбця в таблицю розкладу
    //5.2.1.2. Якщо не вдалося отримати рядок, виведення повідомлення про помилку
    public static void fillTable(JTable table) {
        //Отримання моделі таблиці table як DefaultTableModel для маніпуляції даними в таблиці розкладу
        DefaultTableModel scheduleModel = (DefaultTableModel) table.getModel();

        //Очищення стовбців таблиці
        scheduleModel.setNumRows(0);
        // Якщо таблиця не містить стовбців
        if (scheduleModel.getColumnCount() == 0) {
            //Додавання стовбців до таблиці
            for (String column : columns) {
                scheduleModel.addColumn(column);
            }
        }

        //Налаштування ширини стовбця "Дисципліна"
        TableColumn columnSubject = table.getColumnModel().getColumn(0);
        columnSubject.setPreferredWidth(215);

        //Налаштування ширини стовбця "Подія"
        TableColumn columnLesson = table.getColumnModel().getColumn(1);
        columnLesson.setPreferredWidth(200);

        for (ArrayList<Object> rowData : dataForTable) {
            // Отримуємо даних з рядка
            String endHour = (String) rowData.get(Schedule_List_Model.findColumn("hour_end"));
            String endMinute = (String) rowData.get(Schedule_List_Model.findColumn("minute_end"));

            //Якщо поточний час раніше, ніж час закінчення даного уроку
            if (Time_Service.isBeforeSomeTime(Integer.parseInt(endHour), Integer.parseInt(endMinute))) {
                ArrayList<Object> disciplineRow = new ArrayList();
                //Отримання ідентифікаційного коду дисципліни
                String disciplineId = (String) rowData.get(Schedule_List_Model.findColumn("subject_ID"));
                String result = Database_Service.getRowByID("Subject", Integer.parseInt(disciplineId), disciplineRow);
                //Якщо вдалося отримати рядок предмета за ідентифікаційним кодом
                if (result.equals("successfull")) {
                    String discipline = (String) disciplineRow.get(1);
                    String startHour = (String) rowData.get(Schedule_List_Model.findColumn("hour_start"));
                    String startMinute = (String) rowData.get(Schedule_List_Model.findColumn("minute_start"));
                    String lessonType = (String) rowData.get(Schedule_List_Model.findColumn("event_type"));
                    String lessonName = (String) rowData.get(Schedule_List_Model.findColumn("event_name"));

                    // Формування стовбця уроку для таблиці розкладу
                    String startTime = startHour + ":" + startMinute;
                    String endTime = endHour + ":" + endMinute;
                    String lesson = lessonType + ": " + lessonName;

                    Object[] newRowData = {discipline, lesson, startTime, endTime};
                    //Добавлення даного рядка в 
                    scheduleModel.addRow(newRowData);
                //Якщо такого рядка немає
                } else {
                    //Виведення повідомлення про помилку
                    Messages_UI.showErrorMessage(result);
                    return;
                }
            }
        }
    }
    
    //Отримання даних розкладу
    public static String receiveDataForSchedule(ArrayList<String> parameters, ArrayList<Object> values) {
        //Отримання даних розкладу
        String result = Database_Service.retreiveDataByParameters(parameters, values, "*", "ScheduleEvent");
        //Якщо дані отримані успішно
        if (result.equals("successfull")) {
            //Вилучення отриманих даних
            String result_getData = Database_Service.getData(Schedule_List_Model.getScheduleData());
            //Якщо не вдалося вилучити дані
            if (!result_getData.equals("successfull")) {
                return result_getData;
            }
            //Вилучення назв стовбців
            String result_getColumn = Database_Service.getColumns(Schedule_List_Model.getScheduleColumns());
            //Якщо не вдалося вилучити назви стовбців
            if (!result_getColumn.equals("successfull")) {
                return result_getData;
            }
            return "successfull";
        } else {
            return result;
        }
    }

    //Функція ініціалізації масиву даних
    public static void init() {
        if (dataForTable == null) {
            dataForTable = new ArrayList<>(Schedule_List_Model.getScheduleData());
        }
    }

    //Функція-стартер функції отримання даних розкладу
    public static String getData() {
        //Формування списку назв параметрів
        ArrayList<String> parameters = new ArrayList();
        parameters.add("instructor_ID");
        parameters.add("day_of_week");
        //Формування списку параметрів
        ArrayList<Object> values = new ArrayList();
        values.add(User_Model.getId());
        values.add(Time_Service.getCurrentDayOfWeek());
        //Ініціалізації списків розкладу
        Schedule_List_Model.initialization();
        //Отримання даних розкладу
        return receiveDataForSchedule(parameters, values);
    }
}
