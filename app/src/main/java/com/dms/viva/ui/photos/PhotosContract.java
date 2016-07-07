package com.dms.viva.ui.photos;

import android.content.Context;

import com.dms.viva.core.PhotoPost;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface PhotosContract {

    interface View {

        void showErrors();

        void showPhotos(List<PhotoPost> photos);

        void showSuccessSavePhoto();

        void showProgress(final boolean show);

        void showLogoutAlert();

        void showLoginScreen();

        void showPhotoDetails(PhotoPost photo);

        void showSavePhotoAlert(PhotoPost photo);
    }

    interface UserActionsListener {

        void showPhotos();

        void clickPhotoDetails(PhotoPost photo);

        void longClickPhoto(PhotoPost photo);

        void clickLogout();

        void confirmLogout(Context context);

        void confirmSavePhoto(Context context, PhotoPost photo);

    }
}
