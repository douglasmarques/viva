package com.dms.vivanttest.ui.photodetail;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dms.vivanttest.R;
import com.dms.vivanttest.core.PhotoPost;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoDetailFragment extends Fragment implements PhotoDetailContract.View {

    static final String PHOTO_PARAM = "PHOTO_PARAM";

    @Bind(R.id.photographer)
    TextView photographer;

    @Bind(R.id.likes)
    TextView likes;

    @Bind(R.id.captionText)
    TextView captionText;

    @Bind(R.id.favourite)
    ImageView favourite;

    private boolean isFavourite = false;
    private PhotoDetailPresenter presenter;

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
        View viewRoot = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        ButterKnife.bind(this, viewRoot);

        return viewRoot;
    }

    @Override
    public void showPhotoDetail(PhotoPost photo) {
        photographer.setText(getString(R.string.by_photographer, photo.getPhotographer()));
        likes.setText(String.valueOf(photo.getNumberOfLikes()));
        captionText.setText(photo.getCaption());
    }

    @OnClick(R.id.favourite)
    public void clickFavourite() {
        presenter.favouritePhoto();

    }

    @Override
    public void favouritePhoto() {
        favourite.setEnabled(false);
        favourite.setClickable(false);
        favourite.animate().scaleXBy(0.4f).scaleYBy(0.4f).setDuration(100).setListener(scaleUpListener);
    }


    private void dependencyInjection() {
        presenter = new PhotoDetailPresenter(this);
    }

    private Animator.AnimatorListener scaleUpListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            favourite.animate().scaleXBy(-0.4f).scaleYBy(-0.4f).setDuration(100).setListener(scaleDownListener);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }
    };

    private Animator.AnimatorListener scaleDownListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if(isFavourite){
                favourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                isFavourite = false;
            }else{
                favourite.setImageResource(R.drawable.ic_favorite_black_24dp);
                isFavourite = true;
            }
            favourite.setEnabled(true);
            favourite.setClickable(true);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }
    };
}
