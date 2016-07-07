package com.dms.vivanttest.ui.photos;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dms.vivanttest.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests for the photos screen, the main screen which contains a grid of all photos.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PhotosScreenTest {

    @Rule
    public ActivityTestRule<PhotosActivity> activityTestRule =
            new ActivityTestRule<>(PhotosActivity.class);

    @Test
    public void clickLogoff_showAlert() throws Exception {
        onView(withId(R.id.action_logout)).perform(click());
        onView(withText(R.string.ask_logoff)).check(matches(isDisplayed()));
        onView(withText(R.string.yes)).check(matches(isDisplayed()));
        onView(withText(R.string.no)).check(matches(isDisplayed()));
        onView(withText(R.string.no)).perform(click());
    }

    @Test
    public void clickPhoto_opensDetails() throws Exception {
        onView(withId(R.id.photos)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.photo)).check(matches(isDisplayed()));
    }
}