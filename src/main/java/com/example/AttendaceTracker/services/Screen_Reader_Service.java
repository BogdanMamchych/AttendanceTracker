package com.example.AttendaceTracker.services;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

/**
 *
 * Цей клас надає сервіси для читання тексту з екрану. Він включає методи як для
 * автоматичного, так і для ручного видобування тексту.
 */
public class Screen_Reader_Service {

    private String zoomWindowTitleRussian = "Zoom Конференция";
    //Об'єкт Tesseract для розпізнавання тексту;
    private ITesseract tesseract;
    //Зоображення екрану для обробки;
    private BufferedImage screenshot;
    //Об'єкт Robot для отримання скріншоту екрану.
    private Robot robot;
    //Точки початку і кінця області для зчитування тексту.
    private Point startPoint, endPoint;

    private void initalizeTesseract() {
        tesseract = new Tesseract();
        tesseract.setDatapath("tessdata"); // Шлях до теки з даними Tesseract
        tesseract.setLanguage("ukr+rus");
    }

    private void initializeRobot() throws AWTException {
        robot = new Robot();
    }

    private void libraryDownload() {
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        tesseract.setDatapath(tessDataFolder.getAbsolutePath());
    }

    private void initialize() {
        initalizeTesseract();
        try {
            initializeRobot();
        } catch (AWTException ex) {
            Logger.getLogger(Screen_Reader_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        libraryDownload();
    }
    
    

    //Функція для зчитування інформації про відвідуваність в автоматичному режимі;
    public void autoReadText() {
        if (tesseract == null && robot == null) {
            initialize();
        }

    }
    
        private static List<String> extractParticipants(String text) {
            
        List<String> participants = new ArrayList<>();
        // Регулярний вираз для визначення імен учасників
        String regex = "\\b[А-Яа-яІіЇїЄє]+-[А-Яа-яІіЇїЄє]+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String name = capitalizeNameSurname(matcher.group());
            // Додавання імені до списку тільки якщо воно не закінчується на "..."
            if (!name.endsWith("...")) {
                participants.add(name);
            }
        }
        return participants;
    }
        
        // Метод для підвищення регістру після дефісу
    private static String capitalizeNameSurname(String word) {
        StringBuilder sb = new StringBuilder();
        String[] parts = word.split("-");
        for (String part : parts) {
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1)).append("-");
        }
        sb.deleteCharAt(sb.length() - 1); // Видалення зайвого дефісу з кінця
        return sb.toString();
    }

    // Метод для видалення дублікатів зі списку
    private static List<String> removeDuplicates(List<String> list) {
        Set<String> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

    //Функція для зчитування інформації про відвідуваність з вибраної області
    public void readTextFromSelectedArea() {
        if (tesseract == null && robot == null) {
            initialize();
        }
    }

}
