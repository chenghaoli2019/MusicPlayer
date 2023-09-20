package com.music.musicclient.entity;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

public class ListComment {
    BigInteger MusicId;
    List<Comment> Commentlist;

    public ListComment(BigInteger musicId, List<Comment> commentlist) {
        MusicId = musicId;
        Commentlist = commentlist;
    }

    public BigInteger getMusicId() {
        return MusicId;
    }

    public void setMusicId(BigInteger musicId) {
        MusicId = musicId;
    }

    public List<Comment> getCommentlist() {
        return Commentlist;
    }

    public void setCommentlist(List<Comment> commentlist) {
        Commentlist = commentlist;
    }
}



