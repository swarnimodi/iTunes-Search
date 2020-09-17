package com.swarnimodi.itunessearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://itunes.apple.com/";

    @GET("search")
    Call<SearchResult> getSearchResults(@Query("term") String searchTerm, @Query("limit") int limit);
}
