package com.dms.viva.data.repository;


import com.dms.viva.data.remote.RemoteService;
import com.dms.viva.core.PhotoPostsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepositoryImpl implements PhotoRepository{

    private final RemoteService service;

    public PhotoRepositoryImpl(RemoteService service){
        this.service = service;
    }

    @Override
    public void getPhotos(final GetPhotosCallback callback) {
        Call<PhotoPostsResponse> call = service.getPhotos();
        call.enqueue(new Callback<PhotoPostsResponse>() {
            @Override
            public void onResponse(Call<PhotoPostsResponse> call, Response<PhotoPostsResponse> response) {
                callback.onResultSuccess(response.body().getPosts());
            }

            @Override
            public void onFailure(Call<PhotoPostsResponse> call, Throwable t) {
                callback.onResultFail(ERROR_UNKNOWN);
            }
        });
    }
}
