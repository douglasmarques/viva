package com.dms.vivanttest.data.repository;


import com.dms.vivanttest.core.PhotoPostsResponse;
import com.dms.vivanttest.data.remote.RemoteService;

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
