package com.swarnimodi.itunessearch;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://itunes.apple.com/";

    @GET("search")
    Call<ResultModel> getSearchResults();
}
