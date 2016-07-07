package com.dms.viva.data.remote;

import com.dms.viva.core.PhotoPostsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public interface RemoteService {

    String ENDPOINT = "https://files.vivant.com.au/tech_exam/";

    String PHOTO_ENDPOINT = RemoteService.ENDPOINT + "photos/";

    @GET("photo_posts.json")
    Call<PhotoPostsResponse> getPhotos();

    class Builder {

        public static RemoteService build() {
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(new OkHttpClient())
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit.create(RemoteService.class);
        }
    }

}
