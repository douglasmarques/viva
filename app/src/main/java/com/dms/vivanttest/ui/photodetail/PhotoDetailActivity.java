package com.dms.vivanttest.ui.photodetail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.dms.vivanttest.R;
import com.dms.vivanttest.core.PhotoPost;
import com.dms.vivanttest.ui.base.BaseActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoDetailActivity extends BaseActivity {

    public static final String PHOTO_PARAM = "PHOTO_PARAM";

    @Bind(R.id.photo)
    ImageView photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (null == savedInstanceState) {
            if (getIntent().hasExtra(PHOTO_PARAM)) {
                PhotoPost photoPost = (PhotoPost) getIntent().getSerializableExtra(PHOTO_PARAM);

                setActionBarTitle(photoPost.getPhotoFileName().replace(".jpg", ""));

                Callback mCallback = new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap mainImage = ((BitmapDrawable) photo.getDrawable()).getBitmap();
                        if (mainImage != null) {
                            int color = getDominantColor(mainImage);
                            photo.setBackgroundColor(color);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                };

                Uri uri = Uri.parse("https://files.vivant.com.au/tech_exam/photos/" + photoPost.getPhotoFileName());

                Picasso.with(PhotoDetailActivity.this)
                        .load(uri)
                        .into(photo, mCallback);

                initFragment(R.id.containerPhotoDetail, PhotoDetailFragment.newInstance(photoPost));


            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public static int getDominantColor(Bitmap bitmap1) {
        int color = bitmap1.getPixel(1, 1);
        return color;
    }
}
