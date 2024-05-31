/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import com.example.AttendaceTracker.ui.Messages_UI;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config_Service {
    private static Properties properties;
    
    public static void getProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch(IOException ex) {
            Messages_UI.showErrorMessage(ex.toString());
        }
    }
    
    public static String getWhiteListEmail() {
        return properties.getProperty("whitelist.emails");
    }
    
    public static String getWhiteListDomain() {
        return properties.getProperty("whitelist.domain");
    }
    
    public static String getDatabaseUrl() {
        return properties.getProperty("database.url");
    }
    
    public static String getDatabaseLogin() {
        return properties.getProperty("database.login");
    }
    
    public static String getDatabasePassword() {
        return properties.getProperty("database.password");
    }
    
}
