package com.music.musicclient.entity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Blob;
import java.util.List;

import lombok.Data;


@Data
public class Music {

    /**
     * id
     */

    private BigInteger id;

    /**
     * name
     */

    private String name;

    /**
     * uploader
     */

    private String uploader;

    /**
     * account
     */

    private String account;

    /**
     * views
     */

    private Integer views;

    /**
     * likes
     */

    private Integer likes;

    /**
     * musicFiles
     */

    private String musicFiles;




}
