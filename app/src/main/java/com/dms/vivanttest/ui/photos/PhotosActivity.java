package com.dms.vivanttest.ui.photos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.dms.vivanttest.R;
import com.dms.vivanttest.core.PhotoPost;
import com.dms.vivanttest.data.remote.RemoteService;
import com.dms.vivanttest.data.repository.PhotoRepositories;
import com.dms.vivanttest.data.repository.PhotoRepository;
import com.dms.vivanttest.data.repository.UserRepositories;
import com.dms.vivanttest.ui.base.BaseActivity;
import com.dms.vivanttest.ui.login.LoginActivity;
import com.dms.vivanttest.ui.photodetail.PhotoDetailActivity;
import com.dms.vivanttest.ui.widget.VerticalGridRecyclerView;

import java.util.List;

import butterknife.Bind;

public class PhotosActivity extends BaseActivity implements PhotosContract.View {

    @Bind(R.id.photos)
    VerticalGridRecyclerView photos;

    @Bind(R.id.progress)
    ProgressBar progress;

    private PhotosPresenter presenter;
    private PhotosAdapter adapter;
    private OnPhotoClickListener itemListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        dependencyInjection();
        setActionBarTitle(R.string.title_photo_posts);
        presenter.showPhotos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                UserRepositories.getInMemoryRepoInstance(this).logout();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dependencyInjection(){
        itemListener = new OnPhotoClickListener() {
            @Override
            public void onPhotoClick(PhotoPost photo) {
                Intent intent = new Intent(PhotosActivity.this, PhotoDetailActivity.class);
                intent.putExtra(PhotoDetailActivity.PHOTO_PARAM, photo);
                startActivity(intent);
            }
        };
        PhotoRepository repository = PhotoRepositories.
                getInMemoryRepoInstance(RemoteService.Builder.build());
        presenter = new PhotosPresenter(this, repository);
    }

    @Override
    public void showErrors() {

    }

    @Override
    public void showPhotos(List<PhotoPost> photosResult) {
        if (adapter == null) {
            adapter = new PhotosAdapter(this, photosResult, itemListener);
            photos.setAdapter(adapter);
        } else {
            adapter.replaceData(photosResult);
        }
    }

    @Override
    public void showProgress(boolean show) {

    }

    public interface OnPhotoClickListener {
        void onPhotoClick(PhotoPost photo);
    }
}
