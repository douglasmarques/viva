package com.dms.vivanttest.ui.intro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dms.vivanttest.R;
import com.dms.vivanttest.ui.login.LoginActivity;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_photo_list), getString(R.string.intro_photo_list_desc), R.drawable.ic_skip_white_24px, Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_photo_details), getString(R.string.intro_photo_details_desc), R.drawable.ic_done_white_24px, Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_favourite_photo), getString(R.string.intro_favourite_photo_desc), R.drawable.ic_done_white_24px, Color.parseColor("#3F51B5")));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this, LoginActivity.class));
    }

}

