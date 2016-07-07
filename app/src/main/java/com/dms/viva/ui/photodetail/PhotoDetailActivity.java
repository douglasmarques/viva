package com.dms.viva.ui.photodetail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.dms.viva.R;
import com.dms.viva.core.PhotoPost;
import com.dms.viva.ui.base.BaseActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.dms.viva.data.remote.RemoteService.PHOTO_ENDPOINT;

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
                Uri uri = Uri.parse(PHOTO_ENDPOINT + photoPost.getPhotoFileName());

                Picasso.with(PhotoDetailActivity.this)
                        .load(uri)
                        .into(photo);
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
