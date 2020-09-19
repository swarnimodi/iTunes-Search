package com.swarnimodi.itunessearch;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    @TypeConverter
    public ArrayList<Song> storedStringToSongs(String value) {

        Gson gson = new Gson();
        if (value == null) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<List<Song>>() {}.getType();
        return gson.fromJson(value, listType);

    }

    @TypeConverter
    public String songsToStoredString(ArrayList<Song> songList) {

        Gson gson = new Gson();
        return gson.toJson(songList);

    }
}
