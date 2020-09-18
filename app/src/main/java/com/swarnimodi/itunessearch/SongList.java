package com.swarnimodi.itunessearch;

import java.util.ArrayList;

public class SongList {

    private ArrayList<Song> songList;

    public SongList(ArrayList<Song> songList) {
        this.songList = songList;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }
}
