/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AttendaceTracker.services;

import com.example.AttendaceTracker.services.Greetings_Service;
import com.microsoft.aad.msal4j.DeviceCodeFlowParameters;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.PublicClientApplication;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author boiik
 */
public class Authentication_Service {
    private static final String clientId = "8cbb4f6d-0b15-4073-8cb0-6e24bacdc73a";
    private static final String authority = "https://login.microsoftonline.com/c603a4af-bdb4-496d-903e-e2b63e0ec56e";
    private static final Set<String> scopes = Collections.singleton("User.Read");
    private CompletableFuture<IAuthenticationResult> future;
    private static String accessToken = "";
    
    public void signIn(JTextField area, Consumer<Boolean> callback) throws MalformedURLException {
    PublicClientApplication app = PublicClientApplication.builder(clientId)
            .authority(authority).build();

    future = app.acquireToken(
            DeviceCodeFlowParameters.builder(scopes, deviceCodeConsumer -> {
                String message = deviceCodeConsumer.message();
                String code = getAuthenticationCode(message);

                SwingUtilities.invokeLater(() -> {
                    area.setText(code);
                    try {
                        Desktop.getDesktop().browse(new URI(deviceCodeConsumer.verificationUri()));
                    } catch (URISyntaxException | IOException e) {
                        e.printStackTrace();
                    }
                });

            }).build()
    );

    CompletableFuture.runAsync(() -> {
        try {
            IAuthenticationResult result = future.get();
            accessToken = result.accessToken();
            // Викликаємо callback з результатом
            callback.accept(true);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Викликаємо callback з помилкою
            callback.accept(false);
        }
    });
}


    

    
    public String getAccessToken() {
        return accessToken;
    }

    private String getAuthenticationCode(String string) {
        // Паттерн для пошуку фрагментів, які складаються з 9 великих літер
        Pattern pattern = Pattern.compile("\\b[A-Z0-9]{9}\\b");

        // Пошук фрагмента у вихідному рядку
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            String extractedFragment = matcher.group();
            return extractedFragment;
        } else {
            System.out.println("Fragment not found in the original string.");
            return "error";
        }
    }
}
