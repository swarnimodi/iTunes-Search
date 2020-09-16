package com.swarnimodi.itunessearch;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class MainActivityViewModel extends ViewModel{

    private MutableLiveData <List<Song>> mSongs;
    private SongRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mSongs != null) {
            return;
        }
        mRepo = SongRepository.getInstance();
        mSongs = mRepo.getSongs();
    }

    public void newSearch(final List<Song> songs) {
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mSongs.postValue(songs);
                mIsUpdating.setValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();
    }

    public LiveData<List<Song>> getSongs() {
        return mSongs;
    }

    public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }
}
