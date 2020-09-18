package com.swarnimodi.itunessearch;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {

    private static SongRepository instance;
    private ArrayList<Song> dataset = new ArrayList<>();
    RoomDB database;

    public static SongRepository getInstance() {
        if (instance == null) {
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

    public MutableLiveData<List<Song>> getSongs() {
        dataset.add(new Song("", "", ""));
        MutableLiveData<List<Song>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setSongs(Context context, String searchTerm) {

        //get search results here
        //add them like --> dataset.add(new Song(name:"...", artist:"...", album:"..."));

        Controller controller = new Controller(context, searchTerm);
        controller.start();
        database = RoomDB.getInstance(context);
        SongData RoomData = database.songDao().getSongs(searchTerm);

        if (RoomData == null) {
            if (Controller.isWorking) {
                dataset.clear();
                dataset.addAll(Controller.songs);
                dataset.add(new Song("", "", ""));
                dataset.add(new Song("", "", ""));

                RoomData = new SongData();
                RoomData.setSearchTerm(searchTerm);
                RoomData.setSongList(new SongList(dataset));
                database.songDao().insert(RoomData);
            }
            else{
                Toast.makeText(context, "Internet not working", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            SongList data = RoomData.getSongList();
            dataset.clear();
            dataset.addAll(data.getSongList());
            dataset.add(new Song("", "", ""));
            dataset.add(new Song("", "", ""));
        }
    }
}
