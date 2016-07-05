package com.dms.vivanttest.ui.photodetail;

import com.dms.vivanttest.core.PhotoPost;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface PhotoDetailContract {

    interface View {

        void showPhotoDetail(PhotoPost photo);

        void favouritePhoto();

    }

    interface UserActionsListener{

        void showPhotoDetail(PhotoPost photo);

        void favouritePhoto();

    }
}
