package com.swarnimodi.itunessearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://itunes.apple.com/";

    @GET("search")
    Call<List<Song>> getSearchResults(@Query("#searchTerm") String searchTerm);
}
