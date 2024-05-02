/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.AttendaceTracker.ui;

import com.example.AttendaceTracker.model.User_Model;
import com.example.AttendaceTracker.services.Authentication_Service;
import com.example.AttendaceTracker.services.Greetings_Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sign_In_Outlook_UI extends javax.swing.JFrame {
    //Клас Sign_In_Outlook_UI відповідає за відображення вікна входу в Outlook,
    // надання можливості користувачу отримати код, отримати новий код
    // та повернення до вікна вибору способу входу
    
    //Об'єкт для викликання входу
    Authentication_Service authenticationService = new Authentication_Service();
    //Об'єкт для виклику повідомлень
    Messages_UI messagesUi = new Messages_UI();

    //Функція-стартер входу в Outlook
    private void startSignInOutlook() throws MalformedURLException {
        Greetings_Service greetingsService = new Greetings_Service();
        //Запуск функції входу в Outlook
        authenticationService.signInOutlook(codeTextField, result -> {
            if (result) {
                try {
                    //Якщо вдалося увійти
                    authenticationService.UserInfo();
                    backButton.setEnabled(false);
                    getNewCodeButton.setEnabled(false);
                    //Привітання користувача, який увійшов
                    greetingsService.greeting(codeTextField);
                    super.dispose();
                    Main_UI mainMenu = new Main_UI();
                    //Встановлення повного імені(ім'я + прізвище) та
                    // електронної пошти користувача
                    String username = User_Model.getName() + User_Model.getSurname();
                    mainMenu.setNameLabel(username);
                    mainMenu.setEmailLabel(User_Model.getEmail());
                    mainMenu.setVisible(true);
                    //Перехід в головне меню
                } catch (IOException ex) {
                    messagesUi.showErrorMessage(ex.toString());
                }
            } else {
                //У разі відсутності з'єднання
                greetingsService.connectingError(codeTextField);
                //Повідомлення про відсутність з'єдання
            }
        });
    }
    
    public Sign_In_Outlook_UI() {
        try {
            initComponents();
            startSignInOutlook();
        } catch (MalformedURLException ex) {
            messagesUi.showErrorMessage(ex.toString());
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

        titleLabel1 = new javax.swing.JLabel();
        codeTextField = new javax.swing.JTextField();
        getNewCodeButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel1.setText("Для входу в Outlook, введіть код:");

        codeTextField.setEditable(false);
        codeTextField.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        codeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codeTextField.setText("Отримайте код");

        getNewCodeButton.setText("Отримати новий код");
        getNewCodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getNewCodeButtonActionPerformed(evt);
            }
        });

        backButton.setText("Назад");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(codeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(getNewCodeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getNewCodeButton)
                    .addComponent(backButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Кнопка "Отримати новий код"
    private void getNewCodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getNewCodeButtonActionPerformed
        //Скасування нинішнього процесу входу
        authenticationService.cancelSignIn();
        try {
            //Запуск нового входу
            startSignInOutlook();
        } catch (MalformedURLException ex) {
            //У разі помилки
            messagesUi.showErrorMessage(ex.toString());
        }
    }//GEN-LAST:event_getNewCodeButtonActionPerformed

    //Кнопка "Назад"
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        //Скасування нинішнього процесу входу
        authenticationService.cancelSignIn();
        super.dispose();
        Sign_In_UI signInUi = new Sign_In_UI();
        signInUi.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    //Опис компонентів вікна:
    //backButton - Кнопка "Назад";
    //codeTextField - Текстовий поле для відображення коду, повідомлення про
    // відсутність з'єднання та/або повідомлення "Ласкаво просимо" після входу;
    //getNewCodeButton - Кнопка "Отримати новий код";
    //jLabel1 - Текст "Для входу в Outlook, введіть код:"
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JButton getNewCodeButton;
    private javax.swing.JLabel titleLabel1;
    // End of variables declaration//GEN-END:variables
}
