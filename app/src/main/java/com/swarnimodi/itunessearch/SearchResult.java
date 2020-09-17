package com.swarnimodi.itunessearch;

import java.util.List;

public class SearchResult {

    private int resultCount;
    private List<Song> results= null;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Song> getSongs() {
        return results;
    }

    public void setSongs(List<Song> results) {
        this.results = results;
    }
}
