package com.swarnimodi.itunessearch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

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

    @SuppressLint("StaticFieldLeak")
    public void newSearch(final Context context, final String search_term) {

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //send search request
                mSongs = mRepo.getSongs(context, search_term);
                //after search results change, update
                List<Song> songs = mSongs.getValue();
                mSongs.postValue(songs);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(0);
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
}
