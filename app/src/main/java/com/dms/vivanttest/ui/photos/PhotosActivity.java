package com.dms.vivanttest.ui.photos;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.dms.vivanttest.R;
import com.dms.vivanttest.core.PhotoPost;
import com.dms.vivanttest.data.remote.RemoteService;
import com.dms.vivanttest.data.repository.PhotoRepositories;
import com.dms.vivanttest.data.repository.PhotoRepository;
import com.dms.vivanttest.ui.base.BaseActivity;
import com.dms.vivanttest.ui.widget.VerticalGridRecyclerView;

import java.util.List;

import butterknife.Bind;

public class PhotosActivity extends BaseActivity implements PhotosContract.View {

    @Bind(R.id.photos)
    VerticalGridRecyclerView photos;

    @Bind(R.id.progress)
    ProgressBar progress;

    PhotosPresenter presenter;
    PhotosAdapter adapter;
    OnPhotoClickListener itemListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        dependencyInjection();
        presenter.showPhotos();
    }

    private void dependencyInjection(){
        itemListener = new OnPhotoClickListener() {
            @Override
            public void onPhotoClick(PhotoPost photo) {

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
