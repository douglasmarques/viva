package com.dms.viva.ui.photodetail;

import com.dms.viva.core.PhotoPost;

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
