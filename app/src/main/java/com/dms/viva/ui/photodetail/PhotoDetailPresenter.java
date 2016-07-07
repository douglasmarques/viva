package com.dms.viva.ui.photodetail;

import android.support.annotation.NonNull;

import com.dms.viva.core.PhotoPost;

/**
 * Listens to user actions from the UI ({@link PhotoDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
public class PhotoDetailPresenter implements PhotoDetailContract.UserActionsListener {

    @NonNull
    private final PhotoDetailContract.View view;

    PhotoDetailPresenter(@NonNull PhotoDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void showPhotoDetail(PhotoPost photo) {
        view.showPhotoDetail(photo);
    }

    @Override
    public void favouritePhoto() {
        view.favouritePhoto();
    }
}
