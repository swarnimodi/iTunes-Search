package com.swarnimodi.itunessearch;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class SongData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "search_term")
    private String searchTerm;

    @ColumnInfo(name = "song_list")
    @TypeConverters(Converter.class)
    private SongList songList;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public SongList getSongList() {
        return songList;
    }

    public void setSongList(SongList songList) {
        this.songList = songList;
    }
}
