package com.swarnimodi.itunessearch;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class MainActivityViewModel extends ViewModel{

    private MutableLiveData <List<Song>> mSongs;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private SongRepository mRepo;

    public void init(){
        if(mSongs != null) {
            return;
        }
        mRepo = SongRepository.getInstance();
    }

    public void newSearch(final Context context, final String search_term) {
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //send search request



                //after search is done
                mSongs = mRepo.getSongs(context, search_term);



                //after search results change, update
                List<Song> songs = mSongs.getValue();
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
