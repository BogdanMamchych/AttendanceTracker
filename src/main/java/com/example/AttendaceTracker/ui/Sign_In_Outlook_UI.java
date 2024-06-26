/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.AttendaceTracker.ui;

import com.example.AttendaceTracker.services.Outlook_Authentication_Service;
import com.example.AttendaceTracker.services.Greetings_Service;
import com.example.AttendaceTracker.services.Time_Service;
import com.example.AttendaceTracker.dto.User_DTO;
import com.example.AttendaceTracker.services.Database_Service;
import com.example.AttendaceTracker.services.Schedule_Service;
import com.example.AttendaceTracker.services.Subject_Service;
import java.io.IOException;
import java.net.MalformedURLException;

public class Sign_In_Outlook_UI extends javax.swing.JFrame {
    //Клас Sign_In_Outlook_UI відповідає вікно входу в Outlook,
    // надання можливості користувачу отримати код, отримати новий код
    // та повернення до вікна вибору способу входу

    //Об'єкт для викликання входу
    private final Outlook_Authentication_Service authenticationService = new Outlook_Authentication_Service();

    //Функція-стартер входу в Outlook
    private void startSignInOutlook() throws MalformedURLException {
        Greetings_Service greetingsService = new Greetings_Service();
        //Запуск функції входу в Outlook
        authenticationService.outlookSignIn(codeTextField, result -> {
            if (result) {
                try {
                    //Якщо вдалося увійти
                    authenticationService.UserInfo();
                    authenticationService.getWhitelistProperties();
                    if (authenticationService.emailValidate()) {
                        String res = authenticationService.setUserData();
                        if (res.equals("successfull")) {
                            //Привітання користувача, який увійшов
                            Main_UI mainMenu = new Main_UI();
                            if (mainMenu.initializeMainMenuServices()) {
                                backButton.setEnabled(false);
                                getNewCodeButton.setEnabled(false);
                                greetingsService.greeting(codeTextField);
                                super.dispose();
                                mainMenu.setVisible(true);
                            }
                        } else {
                            Messages_UI.showErrorMessage(res);
                        }
                    } else {
                        Messages_UI.showErrorMessage("Відмова в доступі. Електронна пошта повинна мати закінчення @ust.edu.ua");
                    }
                } catch (IOException ex) {
                    Messages_UI.showErrorMessage(ex.toString());
                }
            }
        });
    }

    public Sign_In_Outlook_UI() {
        try {
            initComponents();
            startSignInOutlook();
        } catch (MalformedURLException ex) {
            Messages_UI.showErrorMessage(ex.toString());
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
            Messages_UI.showErrorMessage(ex.toString());
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
    //backButton - кнопка "Назад";
    //codeTextField - текстовий поле для відображення коду, повідомлення про
    // відсутність з'єднання та/або повідомлення "Ласкаво просимо" після входу;
    //getNewCodeButton - кнопка "Отримати новий код";
    //jLabel1 - текст "Для входу в Outlook, введіть код:"
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JButton getNewCodeButton;
    private javax.swing.JLabel titleLabel1;
    // End of variables declaration//GEN-END:variables
}
