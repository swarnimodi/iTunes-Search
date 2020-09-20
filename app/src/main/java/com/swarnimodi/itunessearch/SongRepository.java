package com.swarnimodi.itunessearch;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        dataset.clear();
        Controller controller = new Controller(context, searchTerm);
        controller.start();
        database = RoomDB.getInstance(context);
        SongData RoomData = database.songDao().getSongs(searchTerm);

        if(Controller.isWorking) {

            Controller.songs.observe((LifecycleOwner) context, new Observer<ArrayList<Song>>() {
                @Override
                public void onChanged(ArrayList<Song> songs) {

                    dataset.addAll(Objects.requireNonNull(Controller.songs.getValue()));
                    dataset.add(new Song("", "", ""));
                    dataset.add(new Song("", "", ""));
                }
            });

            SongData Data = new SongData();
            Data.setSearchTerm(searchTerm);
            Data.setSongList(dataset);
            database.songDao().insert(Data);
        }
        else{
            if(RoomData != null){
                ArrayList<Song> data = RoomData.getSongList();
                dataset.addAll(data);
                dataset.add(new Song("", "", ""));
                dataset.add(new Song("", "", ""));
            }
            else{
                Toast.makeText(context, "An error occured", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
