package com.music.musicclient.entity;

import lombok.Data;

import java.math.BigInteger;
@Data
public class Comment{
    BigInteger ID;
    String sender;
    String content;
    int likes;

    public String getSender(String sender) {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }



    public BigInteger getID() {
        return ID;
    }

    public void setID(BigInteger ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}



