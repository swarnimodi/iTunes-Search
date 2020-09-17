package com.swarnimodi.itunessearch;

public class Song {

    private String artistName, collectionName, trackName;

    public Song() {
    }

    public Song(String trackName, String artistName, String collectionName) {
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
}