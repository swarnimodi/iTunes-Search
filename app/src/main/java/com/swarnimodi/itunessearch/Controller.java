package com.swarnimodi.itunessearch;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<SearchResult> {

    public String term;
    public Context context;
    public static ArrayList<Song> songs = new ArrayList<>();

    public Controller(Context context, String term) {
        this.context = context;
        this.term = term;
    }

    public void start() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        Api api = retrofit.create(Api.class);
        int limit = 20;
        Call<SearchResult> call = api.getSearchResults(term, limit);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
        SearchResult searchResult =  response.body();
        try{
            songs = (ArrayList<Song>) searchResult.getSongs();
        }
        catch (Exception e){
            songs.clear();
        }
    }

    @Override
    public void onFailure(Call<SearchResult> call, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
