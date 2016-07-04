package com.dms.vivanttest.ui.photodetail;

import android.support.annotation.NonNull;

import com.dms.vivanttest.core.PhotoPost;
import com.dms.vivanttest.data.repository.PhotoRepository;
import com.dms.vivanttest.ui.photos.PhotosActivity;
import com.dms.vivanttest.ui.photos.PhotosContract;

import java.util.List;

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
    public void favouritePhoto(boolean favourite) {

    }
}
