package com.swarnimodi.itunessearch;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Song>> {

    public String term;
    public Context context;
    public static List<Song> songs;

    public Controller(Context context, String term) {
        this.context = context;
        this.term = term;
    }

    public void start() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        Api api = retrofit.create(Api.class);
        Call<List<Song>> call = api.getSearchResults(term);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
        songs = response.body();
    }

    @Override
    public void onFailure(Call<List<Song>> call, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}