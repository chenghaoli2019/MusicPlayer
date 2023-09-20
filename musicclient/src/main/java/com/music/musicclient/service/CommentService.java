package com.music.musicclient.service;

import org.springframework.stereotype.Service;
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
public class CommentService {
    private final OAuth2Client oAuth2Client;
    private final MusicApiClient musicApiClient;

    public CommentService(OAuth2Client oAuth2Client, MusicApiClient musicApiClient) {
        this.oAuth2Client = oAuth2Client;
        this.musicApiClient = musicApiClient;
    }
    public boolean UpComment(Comment comment){
        String token = oAuth2Client.getToken();
        Gson gson = new Gson();
        String json = gson.toJson(comment);
        try {
            musicApiClient.sendPostRequest("http://localhost:9999/comment/comment", json, token);

        }
        catch (java.lang.Exception e){
            System.out.println("failed");
        }
        return true;
    }


    public Comment GetComment(BigInteger Id){
        String token = oAuth2Client.getToken();
        Comment comment = new Comment();
        try {
            JsonObject jsonResponse= MusicApiClient.sendGetRequest("http://localhost:9999/comment/comment/" + Id, token);
            comment = parseJson(jsonResponse);



        }
        catch (java.lang.Exception e){
            System.out.println("failed");

        }
        return comment;
    }
    public Comment parseJson(JsonObject json) {
        JsonObject recordJson = json.getAsJsonObject("data");



        Comment comment = new Comment();

        comment.setID(recordJson.get("id").getAsBigInteger());
        comment.setContent(recordJson.get("content").getAsString());
        comment.getSender(recordJson.get("sender").getAsString());
        comment.setLikes(recordJson.get("likes").getAsInt());


        return comment;



    }
}

