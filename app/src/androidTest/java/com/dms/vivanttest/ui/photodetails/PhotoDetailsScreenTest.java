package com.dms.vivanttest.ui.photodetails;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dms.vivanttest.R;
import com.dms.vivanttest.core.PhotoPost;
import com.dms.vivanttest.ui.photodetail.PhotoDetailActivity;
import com.dms.vivanttest.ui.photos.PhotosActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.dms.vivanttest.ui.photodetail.PhotoDetailActivity.PHOTO_PARAM;

/**
 * Tests for photo details screen.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PhotoDetailsScreenTest {

    private PhotoPost photo;

    @Rule
    public ActivityTestRule<PhotoDetailActivity> activityTestRule =
            new ActivityTestRule<>(PhotoDetailActivity.class, false, false);

    @Before
    public void setup(){
        photo = newMockPhoto();
        Intent intent = new Intent();
        intent.putExtra(PHOTO_PARAM, photo);
        activityTestRule.launchActivity(intent);
    }

    @Test
    public void checkPhotoDetails() throws Exception {
        onView(withId(R.id.photographer))
                .check(matches(
                        withText(activityTestRule.getActivity()
                                .getString(R.string.by_photographer, photo.getPhotographer()))));
        onView(withId(R.id.likes))
                .check(matches(
                        withText(String.valueOf(photo.getNumberOfLikes()))));
        onView(withId(R.id.captionText))
                .check(matches(
                        withText(photo.getCaption())));
    }


    private PhotoPost newMockPhoto(){
        PhotoPost photo = new PhotoPost();
        photo.setCaption("Behold, the examiner");
        photo.setIsFavourite(false);
        photo.setNumberOfLikes(12);
        photo.setPhotographer("Jono");
        photo.setPhotoFileName("one.jpg");

        return photo;
    }
}