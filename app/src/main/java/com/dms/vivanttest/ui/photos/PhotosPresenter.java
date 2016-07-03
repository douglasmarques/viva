package com.dms.vivanttest.ui.photos;

import android.support.annotation.NonNull;

import com.dms.vivanttest.core.PhotoPost;
import com.dms.vivanttest.data.repository.PhotoRepository;
import com.dms.vivanttest.data.repository.UserRepository;

import java.util.List;

/**
 * Listens to user actions from the UI ({@link PhotosActivity}), retrieves the data and updates
 * the UI as required.
 */
public class PhotosPresenter implements PhotosContract.UserActionsListener {

    @NonNull
    private final PhotosContract.View view;

    @NonNull
    private final PhotoRepository repository;

    PhotosPresenter(@NonNull PhotosContract.View view,
                    @NonNull PhotoRepository repository) {
        this.view = view;
        this.repository = repository;
    }


    @Override
    public void showPhotos() {
        view.showProgress(true);
        repository.getPhotos(new PhotoRepository.GetPhotosCallback() {
            @Override
            public void onResultSuccess(List<PhotoPost> photos) {
                view.showProgress(false);
                view.showPhotos(photos);
            }

            @Override
            public void onResultFail(int error) {
                view.showProgress(false);
                view.showErrors();
            }
        });
    }

    @Override
    public void showDetail(String photo) {

    }
}
