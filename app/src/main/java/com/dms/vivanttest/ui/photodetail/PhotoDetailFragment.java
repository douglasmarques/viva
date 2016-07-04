package com.dms.vivanttest.ui.photodetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.vivanttest.R;
import com.dms.vivanttest.core.PhotoPost;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoDetailFragment extends Fragment implements PhotoDetailContract.View {

    static final String PHOTO_PARAM = "PHOTO_PARAM";

    @Bind(R.id.photographer)
    TextView photographer;

    @Bind(R.id.likes)
    TextView likes;

    @Bind(R.id.captionText)
    TextView captionText;

    private PhotoDetailPresenter presenter;

    private View viewRoot;

    public static PhotoDetailFragment newInstance(PhotoPost photo) {
        PhotoDetailFragment fragment = new PhotoDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(PHOTO_PARAM, photo);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
        dependencyInjection();
        presenter.showPhotoDetail((PhotoPost) getArguments().getSerializable(PHOTO_PARAM));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        ButterKnife.bind(this, viewRoot);

        return viewRoot;
    }

    @Override
    public void showPhotoDetail(PhotoPost photo) {
        photographer.setText(getString(R.string.by_photographer, photo.getPhotographer()) );
        likes.setText(String.valueOf(photo.getNumberOfLikes()));
        captionText.setText(photo.getCaption());
    }

    @Override
    public void favouritePhoto() {

    }

    @Override
    public void unFavouritePhoto() {

    }

    private void dependencyInjection(){
        presenter = new PhotoDetailPresenter(this);
    }
}
