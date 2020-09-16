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

        dataset.add(new Song("Abhi Abhi","KK","Jism 2"));
        dataset.add(new Song("Aadat","Atif Aslam","Kalyug"));
        dataset.add(new Song("Aankhein Teri","Roop Kumar Rathod","Anwar"));
        dataset.add(new Song("Main Agar Kahoon","Sonu Nigam","Om Shanti Om"));
        dataset.add(new Song("Gulabi Ankhen","Mohammed Rafi","The Train"));
        dataset.add(new Song("Oh Hansini","Kishore Kumar","Zehreela Insaan"));
        dataset.add(new Song("Ae Kash Ke Hum","Kumar Sanu","Kabhi Haan Kabhi Naa"));
        //get search results here
        //add them like --> dataset.add(new Song(name:"...", artist:"...", album:"..."));
    }
}
