package com.dms.vivanttest.ui.photos;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dms.vivanttest.R;
import com.dms.vivanttest.core.PhotoPost;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private Context context;
    private List<PhotoPost> photos;
    private PhotosActivity.OnPhotoClickListener photoClickListener;

    public PhotosAdapter(Context context,
                         List<PhotoPost> photos,
                         PhotosActivity.OnPhotoClickListener photoClickListener) {
        setList(photos);
        this.context = context;
        this.photoClickListener = photoClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);

        return new ViewHolder(view, photoClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        PhotoPost photo = photos.get(position);

        Uri uri = Uri.parse("https://files.vivant.com.au/tech_exam/photos/" + photo.getPhotoFileName());

        Picasso.with(context)
                .load(uri)
                .into(viewHolder.photo);

    }

    public void replaceData(List<PhotoPost> photos) {
        setList(photos);
        notifyDataSetChanged();
    }

    private void setList(List<PhotoPost> photos) {
        this.photos = photos;
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    PhotoPost getItem(int position) {
        return photos.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.photo)
        public ImageView photo;

        private PhotosActivity.OnPhotoClickListener photoClickListener;

        public ViewHolder(View itemView, PhotosActivity.OnPhotoClickListener listener) {
            super(itemView);
            photoClickListener = listener;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.photo_detail)
        void onClick(View v) {
            int position = getAdapterPosition();
            PhotoPost photo = getItem(position);
            photoClickListener.onPhotoClick(photo);
        }
    }
}
