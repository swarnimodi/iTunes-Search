package com.swarnimodi.itunessearch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class MainActivityViewModel extends ViewModel{

    private MutableLiveData <List<Song>> mSongs;
    private SongRepository mRepo;

    public void init(){
        if(mSongs != null) {
            return;
        }
        mRepo = SongRepository.getInstance();
        mSongs = mRepo.getSongs();
    }

    public LiveData<List<Song>> getSongs() {
        return mSongs;
    }
}
