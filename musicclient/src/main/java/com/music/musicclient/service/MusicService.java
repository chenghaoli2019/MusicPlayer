package com.music.musicclient.service;

import org.springframework.stereotype.Service;
import com.music.musicclient.Repo.*;
import com.music.musicclient.entity.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

@Service
public class MusicService {
    private final OAuth2Client oAuth2Client;
    private final MusicApiClient musicApiClient;
    MusicService(OAuth2Client oAuth2Client,MusicApiClient musicApiClient){
        this.oAuth2Client = oAuth2Client;
        this.musicApiClient = musicApiClient;
    }
    public List<Music> ParseJson(JsonObject Json){
        // Create a list of Music objects
        System.out.println("a");
        List<Music> musicList = new ArrayList<>();
        System.out.println("b");
        // Extract "records" array from "data" object
        JsonArray records = Json.getAsJsonObject("data").getAsJsonArray("records");
        System.out.println("c" + records);
        // Iterate over each record and create Music objects
        for (JsonElement recordElement : records) {
            JsonObject recordJson = recordElement.getAsJsonObject();
            System.out.println("d"+ recordElement);
            Music music = new Music();
            music.setId(recordJson.get("id").getAsBigInteger());
            System.out.println("k");
            music.setName(recordJson.get("name").getAsString());
            System.out.println("1");
            music.setUploader(recordJson.get("uploader").getAsString());
            System.out.println("2");
            music.setAccount(recordJson.get("account").getAsString());
            System.out.println("3");
            music.setViews(recordJson.get("views").getAsInt());
            System.out.println("4");
            music.setLikes(recordJson.get("likes").getAsInt());
            System.out.println("6");
            music.setMusicFiles(recordJson.get("musicFiles").getAsString());

            musicList.add(music);
        }

        // Print the list of Music objects
        System.out.println("e");
        for (Music music : musicList) {
            System.out.println("f");
            System.out.println(music);
        }
        System.out.println(musicList);
        return musicList;
    }
    public Music parseJson(JsonObject json) {
        JsonObject recordJson = json.getAsJsonObject("data");



        Music music = new Music();

        music.setId(recordJson.get("id").getAsBigInteger());
        music.setName(recordJson.get("name").getAsString());
        music.setUploader(recordJson.get("uploader").getAsString());
        music.setAccount(recordJson.get("account").getAsString());
        music.setViews(recordJson.get("views").getAsInt());
        music.setLikes(recordJson.get("likes").getAsInt());
        System.out.println("6");
        music.setMusicFiles(recordJson.get("musicFiles").getAsString());
        System.out.println("7");

        return music;



    }
    public List<Music> GetMusic(){
        String token = oAuth2Client.getToken();

        try {
            JsonObject jsonResponse= MusicApiClient.sendGetRequest("http://localhost:9999/music/music/page", token);
            System.out.println(jsonResponse);
            List<Music> playlist = ParseJson(jsonResponse);
            return playlist;




        }
        catch (java.lang.Exception e){
            ArrayList<Music> playlist = new ArrayList<Music>();
            return playlist;
        }


    }
    public boolean upload(Music music){
        String token = oAuth2Client.getToken();
        Gson gson = new Gson();
        System.out.println(music);
        String json = gson.toJson(music);
        System.out.println(json);
        try {
            musicApiClient.sendPostRequest("http://localhost:9999/music/music", json, token);

        }
        catch (java.lang.Exception e){
            System.out.println("failed");
        }
        return true;
    }
    public Music download(BigInteger Id){
        String token = oAuth2Client.getToken();
        Music song = new Music();
        System.out.println("what");
        try {
            System.out.println("hunh?");
            JsonObject jsonResponse= MusicApiClient.sendGetRequest("http://localhost:9999/music/music/" + Id, token);
            song = parseJson(jsonResponse);



        }
        catch (java.lang.Exception e){
            System.out.println("failed");

        }
        return song;
    }

    public boolean like(BigInteger id){
        Music music = download(id);
        music.setLikes(music.getLikes() + 1);
        String token = oAuth2Client.getToken();
        Gson gson = new Gson();
        System.out.println(music);
        String json = gson.toJson(music);
        System.out.println(json);
        try {
            musicApiClient.sendPutRequest("http://localhost:9999/music/music","", json, token);

        }
        catch (java.lang.Exception e){
            System.out.println("failed");
        }
        return true;
    }


    }

