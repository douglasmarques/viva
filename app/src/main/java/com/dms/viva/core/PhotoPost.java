package com.dms.viva.core;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotoPost implements Serializable {

    @SerializedName("photo_file_name")
    private String photoFileName;

    private String photographer;

    private String caption;

    @SerializedName("number_of_likes")
    private Integer numberOfLikes;

    @SerializedName("is_favourite")
    private Boolean isFavourite;

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Boolean getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

}