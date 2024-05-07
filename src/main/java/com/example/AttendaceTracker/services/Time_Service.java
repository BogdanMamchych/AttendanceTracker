/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import com.example.AttendaceTracker.ui.Messages_UI;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Time_Service {

    //Інтерфейс для  слухача подій часу
    private final Time_Interface timeListener;

    public Time_Service(Time_Interface timeListener) {
        this.timeListener = timeListener;
    }

    //Функція оновлення часу
    private void updateModel() {
        LocalDateTime now = LocalDateTime.now();

        //Отримуємо дані про час
        String dayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("uk", "UA"));
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        int hour = now.getHour();
        int minute = now.getMinute();

        //Встановлення дати та часу
        timeListener.setDate(formatDate(upperCaseFirstLetter(dayOfWeek), day, month, year));
        timeListener.setTime(formatTime(hour, minute));
    }

    //Стартер часу в програмі
    public void startDateTime() {
        //Створення планувальника для виконання завдань з використанням одного потока
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        //Створення об'єкту для повторного виконання функції
        Runnable updateTask = () -> {
            updateModel();
        };

        try {
            //Оновлення моделі з частотою 1 раз на секунду
            scheduler.scheduleAtFixedRate(updateTask, 0, 1, TimeUnit.SECONDS);
        } catch (Exception e) {
            //У разі помилки:
            //Виведення повідомлення про помилку
            Messages_UI.showErrorMessage(e.toString());
        }
    }

    //Функція підвищення реєстру першої літери в дні тижня
    private String upperCaseFirstLetter(String dayOfWeek) {
        //Якщо перша літера знаходиться в нижньому реєстрі
        if (Character.isLowerCase(dayOfWeek.charAt(0))) {
            return Character.toUpperCase(dayOfWeek.charAt(0)) + dayOfWeek.substring(1);
        }
        return dayOfWeek;
    }

    //Функція, яка повертає дату за форматом: День тижня, дд/мм/рррр
    private String formatDate(String dayOfWeek, int day, int month, int year) {
        return String.format("%s, %02d/%02d/%02d", dayOfWeek, day, month, year);
    }

    //Функція, яка повертає час за форматом гг:хх
    private String formatTime(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }
    
    //Функція, яка повертає теперішню годину
    public int getCurrentHour() {
        LocalDateTime now = LocalDateTime.now();
        return now.getHour();
    }
    
    //Функція, яка повертає теперішню хвилину
    public int getCurrentMinute() {
        LocalDateTime now = LocalDateTime.now();
        return now.getMinute();
    }
    
    //Функція, яка повертає теперішній день тижня
    public int getCurrentDayOfWeek() {
        LocalDateTime now = LocalDateTime.now();
        return now.getDayOfYear();
    }
    
    //Функція, яка повертає теперішій день
    public int getCurrentDay() {
        LocalDateTime now = LocalDateTime.now();
        return now.getDayOfMonth();
    }
    
    //Функція, яка повертає теперішній місяць
    public String getCurrentMonth() {
        LocalDateTime now = LocalDateTime.now();
        return upperCaseFirstLetter(now.getMonth().toString());
    }
    
    //Функція, яка повертає теперішній рік
    public int getCurrentYear() {
        LocalDateTime now = LocalDateTime.now();
        return now.getYear();
    }
    
    
}
