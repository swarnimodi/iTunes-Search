package com.swarnimodi.itunessearch;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        dataset.add(new Song("Abhi Abhi","KK","Jism 2"));
        dataset.add(new Song("Aadat","Atif Aslam","Kalyug"));
        dataset.add(new Song("Aankhein Teri","Roop Kumar Rathod","Anwar"));
        dataset.add(new Song("Main Agar Kahoon","Sonu Nigam","Om Shanti Om"));
        dataset.add(new Song("Gulabi Ankhen","Mohammed Rafi","The Train"));
        dataset.add(new Song("Oh Hansini","Kishore Kumar","Zehreela Insaan"));
        dataset.add(new Song("Ae Kash Ke Hum","Kumar Sanu","Kabhi Haan Kabhi Naa"));
        dataset.add(new Song("Tum Ho","Mohit Chauhan","Rockstar"));

        //get search results here
        //add them like --> dataset.add(new Song(name:"...", artist:"...", album:"..."));

        Controller controller = new Controller(context, searchTerm);
    }


}
