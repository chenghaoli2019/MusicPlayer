package com.music.musicclient.Repo;
import com.music.musicclient.entity.Music;
import java.util.List;
import org.json.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.*;

import java.util.Collection;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
@Repository
public class MusicApiClient {

    MusicApiClient() {
        try {
            // 设置 API URL
            String apiUrl = "http://localhost:9999/balance/balance";

            // 设置令牌
            String token = "7912c3c1-84a1-418f-bcff-d845d154a138";

            // 发起 GET 请求示例，并添加令牌到请求头


            // 其他请求示例...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JsonObject sendGetRequest(String urlString, String token) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse the response JSON string into a JsonObject
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

        return jsonObject;
    }

    public static void sendPostRequest(String urlString, String requestBody, String token) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(requestBody.getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }

    public static void sendPutRequest(String urlString, String path, String requestBody, String token) throws Exception {
        URL url = new URL(urlString + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(requestBody.getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }
    public static void sendPutRequestWithParams(String urlString, String path, String paramName, double paramValue, String token) throws Exception {
        // Build the parameter string
        StringBuilder parameterString = new StringBuilder();
        String key = URLEncoder.encode(paramName, "UTF-8");
        String value = Double.toString(paramValue);
        parameterString.append(key).append("=").append(value);

        // Append the parameter to the path
        if (parameterString.length() > 0) {
            path += "?" + parameterString.toString();
        }

        // Create the URL and connection
        URL url = new URL(urlString + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setDoOutput(true);

        // Send the request
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Cleanup
        connection.disconnect();
    }

    public static void sendDeleteRequest(String urlString, String token) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }
}

