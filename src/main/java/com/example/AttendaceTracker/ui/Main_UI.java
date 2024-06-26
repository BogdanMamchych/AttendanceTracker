/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.AttendaceTracker.ui;

import com.example.AttendaceTracker.dto.Subject_List_DTO;
import com.example.AttendaceTracker.dto.User_DTO;
import com.example.AttendaceTracker.services.Schedule_Service;
import com.example.AttendaceTracker.services.Subject_Service;
import com.example.AttendaceTracker.services.Time_Interface;
import com.example.AttendaceTracker.services.Time_Service;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main_UI extends javax.swing.JFrame implements Time_Interface {
    //Клас Main_UI відповідає за вікно головного меню та надання
    // можливості користувачу переглядати розклад на сьогодні,
    // натисканням кнопки "Перегляд списку учасників" відкрити
    // вікно перегляду учасників, перегляд часу та натисканням
    // кнопки "Зчитати учасників" перейти до початку процесу зчитування

    private static ScheduledExecutorService scheduler;

    public Main_UI() {
        initComponents();

    }

    //Функція встановлення повного імені користувача
    public void setFullName() {
        fullNameLabel.setText(User_DTO.getFullName());
    }

    //Функція встановлення електронної пошти користувача
    public void setEmail() {
        emailLabel.setText(User_DTO.getEmail());
    }

    //Функція встановлення дати
    @Override
    public void setDate(String date) {
        dateLabel.setText(date);
    }

    //Функція встановлення часу
    @Override
    public void setTime(String time) {
        timeLabel.setText(time);
    }

    //Функція заповнення таблиці розкладу
    public static void fillTable() {
        Schedule_Service.fillTable(dailyScheludeTable);
    }

    //Функція запуску оновлення розкладу
    public static void startScheduleUpdate() {
        //Створення планувальника для виконання завдань з використанням одного потока
        scheduler = Executors.newScheduledThreadPool(1);

        //Створення об'єкту для повторного виконання функції
        Runnable updateTask = () -> {
            fillTable();
        };

        try {
            //Оновлення моделі з частотою 1 раз на секунду
            scheduler.scheduleAtFixedRate(updateTask, 0, 2, TimeUnit.MINUTES);
        } catch (Exception e) {
            //У разі помилки:
            //Виведення повідомлення про помилку
            Messages_UI.showErrorMessage(e.toString());
        }
    }

    //Функція запуску оновлення розкладу
    public static void stopDateTime() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
    
    //Функція налаштування сервісів головного меню
    public boolean initializeMainMenuServices() {
        // Отримання даних для розкладу
        String res_getData = Schedule_Service.getData();
        if (res_getData.equals("successful")) {
            // Ініціалізація сервісу розкладу
            Schedule_Service.init();
            // Ініціалізація сервісу предметів
            Subject_Service.init();

            // Отримання даних предметів
            if (Subject_Service.recieveSubjectData().equals("successful")) {
                // Перетворення отриманих даних у DTO (Data Transfer Object)
                Subject_Service.toDTO();
                // Відправка даних предметів у випадаючий список
                Subject_List_DTO.sendToComboBox(disciplineComboBox);
                // Запуск оновлення розкладу
                startScheduleUpdate();
                // Встановлення повного імені користувача
                setFullName();
                // Встановлення email користувача
                setEmail();
                // Встановлення слухача часу для Time_Service
                Time_Service.setTimeListener(this);
                // Запуск сервісу часу
                Time_Service.startDateTime();
                return true;
            } else {
                // Показ повідомлення про помилку, якщо не вдалося отримати дані предметів
                Messages_UI.showErrorMessage("Вам потрібно додати ваші предмети в базу даних");
                return false;
            }
        } else {
            // Показ повідомлення про помилку, якщо не вдалося отримати дані розкладу
            Messages_UI.showErrorMessage(res_getData);
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleNameLabel = new javax.swing.JLabel();
        fullNameLabel = new javax.swing.JLabel();
        titleEmailLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        readParticipantsButton = new javax.swing.JButton();
        showParticipantsListButton = new javax.swing.JButton();
        titleDisciplineLabel = new javax.swing.JLabel();
        disciplineComboBox = new javax.swing.JComboBox<>();
        titleDateLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        titleTimeLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dailyScheludeTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Головне меню");

        titleNameLabel.setText("Повне ім'я:");

        fullNameLabel.setText("j");

        titleEmailLabel.setText("Email:");

        emailLabel.setText("j");

        readParticipantsButton.setText("Зчитати учасників");
        readParticipantsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readParticipantsButtonActionPerformed(evt);
            }
        });

        showParticipantsListButton.setText("Перегляд списку учасників");
        showParticipantsListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showParticipantsListButtonActionPerformed(evt);
            }
        });

        titleDisciplineLabel.setText("Дисципліна:");

        titleDateLabel.setText("Дата:");

        dateLabel.setText("j");

        titleTimeLabel.setText("Час:");

        timeLabel.setText("j");

        jScrollPane2.setViewportView(dailyScheludeTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleDisciplineLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disciplineComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(readParticipantsButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleTimeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(showParticipantsListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(titleEmailLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleNameLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(titleEmailLabel)
                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titleDateLabel)
                        .addComponent(dateLabel)
                        .addComponent(titleTimeLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showParticipantsListButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(readParticipantsButton)
                    .addComponent(titleDisciplineLabel)
                    .addComponent(disciplineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Кнопки "Перегляд списку учасників"
    private void showParticipantsListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showParticipantsListButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showParticipantsListButtonActionPerformed

    //Кнопка "Зчитати учасників"
    private void readParticipantsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readParticipantsButtonActionPerformed
        Receive_Type_Selection_UI receiveTypeSelectionUi = new Receive_Type_Selection_UI();
        receiveTypeSelectionUi.setVisible(true);
    }//GEN-LAST:event_readParticipantsButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    //Опис компонентів вікна:
    //dailyScheduleTable - таблиця розкладу на один день(сьогодні);
    //dataLabel - відображення дати;
    //disciplineComboBox - випадаючий список дисциплін;
    //emailLabel - відображення електронної пошти користувача;
    //jScrollPane2 - прокрутка для таблиці розкладу;
    //fullNameLabel - повне ім'я користувача;
    //readParticipantsButton - кнопка "Зчитати учасників";
    //showParticipantsListButtton - кнопка "Перегляд списку учасників";
    //timeLabel - відображення часу;
    //titleDatelLabel - текст "Дата:";
    //titleDisciplineLabel - текст "Дисципліна";
    //titleEmailLabel - текст "Email:";
    //titleNameLabel - текст "Повне ім'я:";
    //titleTimeLabel - текст "Час:";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTable dailyScheludeTable;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JComboBox<String> disciplineComboBox;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel fullNameLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton readParticipantsButton;
    private javax.swing.JButton showParticipantsListButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel titleDateLabel;
    private javax.swing.JLabel titleDisciplineLabel;
    private javax.swing.JLabel titleEmailLabel;
    private javax.swing.JLabel titleNameLabel;
    private javax.swing.JLabel titleTimeLabel;
    // End of variables declaration//GEN-END:variables
}
