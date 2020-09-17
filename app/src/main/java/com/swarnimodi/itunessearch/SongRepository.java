package com.swarnimodi.itunessearch;

import android.content.Context;

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

    public MutableLiveData<List<Song>> getSongs(Context context, String search_term) {
        setSongs(context, search_term);
        MutableLiveData<List<Song>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setSongs(Context context, String searchTerm) {

        //get search results here
        //add them like --> dataset.add(new Song(name:"...", artist:"...", album:"..."));

        Controller controller = new Controller(context, searchTerm);
        controller.start();
        dataset.addAll(Controller.songs);
    }


}
