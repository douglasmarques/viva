package com.dms.vivanttest.ui.photos;

import com.dms.vivanttest.core.PhotoPost;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface PhotosContract {

    interface View {

        void showErrors();

        void showPhotos(List<PhotoPost> photos);

        void showProgress(final boolean show);
    }

    interface UserActionsListener{

        void showPhotos();

        void showDetail(String photo);

    }
}
