package com.swarnimodi.itunessearch;

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

    public MutableLiveData<List<Song>> getSongs(String search_term) {
        setSongs(search_term);
        MutableLiveData<List<Song>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setSongs(String searchTerm) {

        dataset.add(new Song("Abhi Abhi","KK","Jism 2"));
        dataset.add(new Song("Aadat","Atif Aslam","Kalyug"));
        dataset.add(new Song("Aankhein Teri","Roop Kumar Rathod","Anwar"));
        dataset.add(new Song("Main Agar Kahoon","Sonu Nigam","Om Shanti Om"));
        dataset.add(new Song("Gulabi Ankhen","Mohammed Rafi","The Train"));
        dataset.add(new Song("Oh Hansini","Kishore Kumar","Zehreela Insaan"));
        dataset.add(new Song("Ae Kash Ke Hum","Kumar Sanu","Kabhi Haan Kabhi Naa"));
        //get search results here
        //add them like --> dataset.add(new Song(name:"...", artist:"...", album:"..."));

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);

        Call<ResultModel> call = api.getSearchResults();
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                ResultModel resultModel = response.body();
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
            }
        });
    }
}
