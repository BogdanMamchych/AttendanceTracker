/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import com.example.AttendaceTracker.model.User_Model;
import com.example.AttendaceTracker.ui.Messages_UI;
import com.microsoft.aad.msal4j.DeviceCodeFlowParameters;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.PublicClientApplication;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.awt.Desktop;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Authentication_Service {
    //Клас Authentication_Service відповідає за керування автентифікацією

    //Клієнтський ідентифікатор
    private static final String clientId = "8cbb4f6d-0b15-4073-8cb0-6e24bacdc73a";

    //Авторитет для автентифікації через Microsoft
    private static final String authority = "https://login.microsoftonline.com/c603a4af-bdb4-496d-903e-e2b63e0ec56e";

    //Область обмеження для автентифікації через Microsoft
    private static final Set<String> scopes = Collections.singleton("User.Read");

    //Об'єкт, що представляє майбутнє значення для результату аутентифікації
    private CompletableFuture<IAuthenticationResult> future;

    //Токен доступу
    private static String accessToken = "";

    //Об'єкт для відображення повідомлень користувачу
    private final Messages_UI messages = new Messages_UI();

    //URL для отримання інформації про користувача
    String graphApiUrl = "https://graph.microsoft.com/oidc/userinfo";

    //Функція входу в Outlook;
    //Етапи виконання функції:
    //1. Створення екземпляру PublicClientApplication для роботи
    // з Azure Active Directory;
    //2. Отримання автентифікаційного коду за допомогою пристрою, 
    // за допомогою acquireToken;
    //3. Відображення отриманого коду в вікні за допомогою текстового рядка field;
    //4. Автоматичне відкриття сайту Microsoft для введення 
    // автентифікаційного коду за допомогою методу browse;
    //4.2. У випадку помилки: Виведення повідомлення про помилку за допомогою
    // messages.showErrorMessage
    //5. Асинхронне виконання з метою уникнення блокування основного потоку
    // за допомогою CompletableFuture.runAsync;
    //У випадку помилки: Виведення повідомлення про помилку за допомогою
    // messages.showErrorMessage
    public void signInOutlook(JTextField field, Consumer<Boolean> callback) throws MalformedURLException {

        //Створення екземпляру PublicClientApplication для роботи
        // з Azure Active Directory
        PublicClientApplication app = PublicClientApplication.builder(clientId)
                .authority(authority).build();

        //Отримання автентифікаційного коду за допомогою пристрою, 
        // за допомогою acquireToken
        future = app.acquireToken(
                DeviceCodeFlowParameters.builder(scopes, deviceCodeConsumer -> {
                    //Отримання повідомлення з кодом
                    String message = deviceCodeConsumer.message();
                    //Отримання автентифікаційного коду з повідомлення
                    String code = getAuthenticationCode(message);
                    
                    SwingUtilities.invokeLater(() -> {
                        //Відправлення автентифікаційного коду до вікна через JTextField
                        field.setText(code);
                        try {
                            //Автоматичне відкриття сайту Microsoft для введення автентифікаційного коду
                            Desktop.getDesktop().browse(new URI(deviceCodeConsumer.verificationUri()));
                        } catch (URISyntaxException | IOException e) {
                            //У разі помилки:
                            //Виведення повідомлення про помилку
                            messages.showErrorMessage(e.toString());
                        }
                    });
                    
                }).build()
        );

        //Асинхронне виконання з метою уникнення блокування основного потоку
        CompletableFuture.runAsync(() -> {
            try {
                IAuthenticationResult result = future.get();
                accessToken = result.accessToken();
                // Викликаємо callback з результатом
                callback.accept(true);
            } catch (InterruptedException | ExecutionException e) {
                //У разі помилки:
                //Виведення повідомлення про помилку
                messages.showErrorMessage(e.toString());
                // Викликаємо callback з помилкою
                callback.accept(false);
            }
        });
    }

    //Фукнція повернення токену доступу
    public String getAccessToken() {
        return accessToken;
    }

    //Функція отримання автентифікаційного коду
    private String getAuthenticationCode(String string) {
        //Паттерн для пошуку фрагментів, які складаються з 9 великих літер(довжина коду)
        Pattern pattern = Pattern.compile("\\b[A-Z0-9]{9}\\b");

        // Пошук фрагмента у вихідному рядку
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            //Якщо автентифікаційий код знайдено знайдено
            String extractedFragment = matcher.group();
            return extractedFragment;
        } else {
            return "Коду немає";
        }
    }
    
    //Функція отримання інформації про користувача(повне ім'я та електронна пошта)
    //Етапи виконання функції:
    //1. Створення об'єкту URL та встановлення з'єднання за допомогою HttpURLConnection;
    //2. Встановлення заголовків та методу запиту за допомогою
    // setRequestProperty та setRequestMethod;
    //3. Виконання запиту та отримання відповіді за допомогою getResponseCode;
    //4. Обробка відповіді:
    //4.1. У випадку успішності виконання:
    //4.1.1. Обробка та зчитування вмісту відповіді за допомогою Scanner та StringBuilder
    //4.1.2. Розбір JSON-об'єта та зчитування повного імені та електронної
    // пошти користувача за допомогою Gson та JsonElement;
    //4.1.3. Розділення повного імені на прізвище та ім'я
    //4.1.4. Збереження прізвища, імені та електронної пошти;
    //4.2. У випадку помилки:
    //4.2.1. Виведення повідомлення про помилку підключення з кодом запиту;
    public void UserInfo() throws MalformedURLException, IOException {
        //Створення URL-об'єкта
        URL url = new URL(graphApiUrl);
        //Встановлення з'єднання
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //Встановлення заголовку та методу запиту
        conn.setRequestProperty("Authorization", "Bearer" + accessToken);
        conn.setRequestMethod("GET");
        
        //Виконання запиту
        int responseCode = conn.getResponseCode();
        
        if (responseCode == 200) {
            //У разі успішності:
            //Обробка та зчитування вмісту відовіді
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            
            //Зчитування повного імені користувача;
            Gson gson = new Gson();
            String username = gson.fromJson(response.toString(), JsonElement.class)
                    .getAsJsonObject()
                    .get("name")
                    .getAsString();
            
            //Розділення повного імені користувача на ім'я та прізвище користувача
            String name = "";
            String surname = "";
            splitUserName(username, name, surname);    
            
            //Збереження імені та прізвища користувача
            User_Model.setName(name);
            User_Model.setSurname(surname);
    
            //Зчитування та збереження електронної пошти користувача
            User_Model.setEmail(gson.fromJson(response.toString(), JsonElement.class)
                                .getAsJsonObject()
                                .get("email")
                                .getAsString());
            
            
        } else {
            //У разі помилки:
            //Виведення повідомлення про помилку підключення з кодом запиту
            messages.showErrorMessage("Помилка підключення: Код: " + responseCode);
        }
    }
    
    //Функція розділення повного імені користувача на дві частини(ім'я та фамілія)
    private void splitUserName(String username, String name, String surname) {
        //Розділення повного імені користувача
        String[] parts = username.split(" ");
        if (parts.length == 2) {
            //У разі, якщо вдалося розділити повне ім'я
            name = parts[0];
            surname = parts[1];
        }
    }

    //Функція скасування виконання входу
    public void cancelSignIn() {
        if (future != null && !future.isDone()) {
            future.cancel(true);
        }
    }
}
