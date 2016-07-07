package com.dms.viva.ui.photos;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dms.viva.core.PhotoPost;
import com.dms.viva.data.remote.RemoteService;
import com.dms.viva.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private Context context;
    private List<PhotoPost> photos;
    private PhotosActivity.OnPhotoClickListener photoClickListener;
    private PhotosActivity.OnPhotoLongClickListener photoLongClickListener;

    public PhotosAdapter(Context context,
                         List<PhotoPost> photos,
                         PhotosActivity.OnPhotoClickListener photoClickListener,
                         PhotosActivity.OnPhotoLongClickListener photoLongClickListener) {
        setList(photos);
        this.context = context;
        this.photoClickListener = photoClickListener;
        this.photoLongClickListener = photoLongClickListener;
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



        viewHolder.photographer.setText(context.getString(R.string.by_photographer,photo.getPhotographer()));
        viewHolder.caption.setText(photo.getCaption());

        Uri uri = Uri.parse(RemoteService.PHOTO_ENDPOINT + photo.getPhotoFileName());
        Picasso.with(context)
                .load(uri)
                .placeholder(R.drawable.photo_placeholder)
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

        @Bind(R.id.photographer)
        public TextView photographer;

        @Bind(R.id.caption)
        public TextView caption;

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

        @OnLongClick(R.id.photo_detail)
        boolean onLongClick(View v) {
            int position = getAdapterPosition();
            final PhotoPost photo = getItem(position);
            photoLongClickListener.onPhotoLongClick(photo);
            return true;
        }
    }
}
