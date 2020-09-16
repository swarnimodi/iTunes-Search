package com.swarnimodi.itunessearch;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {

    private static SongRepository instance;
    private ArrayList<Song> dataset = new ArrayList<>();

    public static SongRepository getInstance() {
        if(instance == null) {
            instance = new SongRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Song>> getSongs() {
        setSongs();
        MutableLiveData<List<Song>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setSongs() {

        //get search results here
        //add them like --> dataset.add(new Song(name:"...", artist:"...", album:"..."));
    }
}
