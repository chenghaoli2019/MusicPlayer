package com.music.musicclient.service;
import com.zaxxer.hikari.pool.HikariProxyResultSet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
@Service
public class LyricService {
    LyricService(){

    }
    public String TxtReader(String file){
        String fileName = file; // Replace with your file name or path
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileContents = stringBuilder.toString();

        return fileContents;
    }
    public static void saveMultipartFile(String location, String fileName, MultipartFile file) {
        try {
            File directory = new File(location);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File savedFile = new File(directory, fileName);
            file.transferTo(savedFile);
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

