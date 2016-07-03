package com.dms.vivanttest.data.repository;

import com.dms.vivanttest.core.PhotoPost;

import java.util.List;

/**
 * Main entry point for accessing photo data.
 */
public interface PhotoRepository {

    int ERROR_UNKNOWN = 1;

    interface GetPhotosCallback {

        void onResultSuccess(List<PhotoPost> photos);

        void onResultFail(int error);
    }

    void getPhotos(GetPhotosCallback callback);

}
