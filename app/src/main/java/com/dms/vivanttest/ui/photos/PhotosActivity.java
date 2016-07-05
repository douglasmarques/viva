package com.dms.vivanttest.ui.photos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.vivanttest.R;
import com.dms.vivanttest.core.PhotoPost;
import com.dms.vivanttest.data.remote.RemoteService;
import com.dms.vivanttest.data.repository.PhotoRepositories;
import com.dms.vivanttest.data.repository.PhotoRepository;
import com.dms.vivanttest.ui.base.BaseActivity;
import com.dms.vivanttest.ui.login.LoginActivity;
import com.dms.vivanttest.ui.photodetail.PhotoDetailActivity;
import com.dms.vivanttest.ui.widget.Alert;
import com.dms.vivanttest.ui.widget.VerticalGridRecyclerView;

import java.util.List;

import butterknife.Bind;

public class PhotosActivity extends BaseActivity implements PhotosContract.View {

    @Bind(R.id.photos)
    VerticalGridRecyclerView photos;

    @Bind(R.id.progress)
    ProgressBar progress;

    @Bind(R.id.empty)
    TextView empty;

    private PhotosPresenter presenter;
    private PhotosAdapter adapter;
    private OnPhotoClickListener itemClickListener;
    private OnPhotoLongClickListener itemLongClickListener;
    private AlertDialog savingDialog;

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
                presenter.clickLogout();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dependencyInjection() {
        itemClickListener = new OnPhotoClickListener() {
            @Override
            public void onPhotoClick(PhotoPost photo) {
                presenter.clickPhotoDetails(photo);
            }
        };
        itemLongClickListener = new OnPhotoLongClickListener() {
            @Override
            public void onPhotoLongClick(PhotoPost photo) {
                presenter.longClickPhoto(photo);
            }
        };
        PhotoRepository repository = PhotoRepositories.
                getInMemoryRepoInstance(RemoteService.Builder.build());
        presenter = new PhotosPresenter(this, repository);
    }

    @Override
    public void showErrors() {
        Toast.makeText(this, R.string.error_unknown_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPhotos(List<PhotoPost> photosResult) {
        if (photosResult != null && !photosResult.isEmpty()) {
            empty.setVisibility(View.GONE);
            if (adapter == null) {
                adapter = new PhotosAdapter(this, photosResult, itemClickListener, itemLongClickListener);
                photos.setAdapter(adapter);
            } else {
                adapter.replaceData(photosResult);
            }
        } else {
            empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showSuccessSavePhoto() {
        Toast.makeText(this, R.string.success_save_photo, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(boolean show) {
        showProgress(show, photos, progress);
    }

    @Override
    public void showLogoutAlert() {
        Alert.showConfirm(this, getString(R.string.ask_logoff),
                getString(R.string.yes),
                getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.confirmLogout(PhotosActivity.this);
                    }
                });
    }

    @Override
    public void showLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void showPhotoDetails(PhotoPost photo) {
        Intent intent = new Intent(PhotosActivity.this, PhotoDetailActivity.class);
        intent.putExtra(PhotoDetailActivity.PHOTO_PARAM, photo);
        startActivity(intent);
    }

    @Override
    public void showSavePhotoAlert(final PhotoPost photo) {
        Alert.showConfirm(this, getString(R.string.ask_save_photo),
                getString(R.string.yes),
                getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.confirmSavePhoto(PhotosActivity.this, photo);
                    }
                });
    }

    public interface OnPhotoClickListener {
        void onPhotoClick(PhotoPost photo);
    }

    public interface OnPhotoLongClickListener {
        void onPhotoLongClick(PhotoPost photo);
    }
}
