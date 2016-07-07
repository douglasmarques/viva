package com.dms.viva.ui.login;

import android.support.design.widget.TextInputLayout;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.dms.viva.R;
import com.dms.viva.data.repository.UserRepository;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests for the login screen
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginScreenTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void clickSignIn_invalidEmail() throws Exception {
        onView(withId(R.id.signIn)).perform(click());
        onView(withId(R.id.userTil)).check(matches(
                withError(activityTestRule.getActivity().getString(R.string.error_invalid_email))));
    }

    @Test
    public void clickSignIn_invalidPassword() throws Exception {
        onView(withId(R.id.userName)).perform(typeText(UserRepository.TEST_USER));
        onView(withId(R.id.signIn)).perform(click());
        onView(withId(R.id.passwordTil)).check(matches(
                withError(activityTestRule.getActivity().getString(R.string.error_invalid_password))));
    }

    @Test
    public void clickSignIn_success() throws Exception {
        onView(withId(R.id.userName)).perform(typeText(UserRepository.TEST_USER));
        onView(withId(R.id.password)).perform(typeText(UserRepository.TEST_PASSWORD));
        onView(withId(R.id.signIn)).perform(click());
        onView(withText(R.string.title_activity_photo_posts)).check(matches(isDisplayed()));
    }


    private static Matcher<View> withError(final String expected) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }
                TextInputLayout til = (TextInputLayout) view;
                if (til.getError() != null) {
                    return til.getError().toString().equals(expected);
                }else{
                    return false;
                }

            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}