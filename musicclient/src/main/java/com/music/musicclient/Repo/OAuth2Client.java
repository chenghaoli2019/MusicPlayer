package com.music.musicclient.Repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public class OAuth2Client {

    public static void main(String[] args) {
        String token = getToken();
        System.out.println("OAuth2 Token: " + token);
    }

    public static String getToken() {
        String clientId = "test";
        String clientSecret = "test";
        String tokenEndpoint = "http://127.0.0.1:9999/auth/oauth2/token";
        String username = "admin";
        String password = "YehdBPev";

        try {
            String credentials = clientId + ":" + clientSecret;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

            URL url = new URL(tokenEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            String requestBody = "grant_type=password&scope=server&username=" + username + "&password=" + password;
            byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBodyBytes, 0, requestBodyBytes.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }
                JSONObject jsonResponse = new JSONObject(response.toString());
                String accessToken = jsonResponse.getString("access_token");
                return accessToken;
            } else {
                System.out.println("Failed to retrieve token. Response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}