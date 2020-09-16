package com.swarnimodi.itunessearch;

import java.util.List;

public class ResultModel {

    private int resultCount;
    private List<Song> songs = null;

    public int getResultCount() {
        return resultCount;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
