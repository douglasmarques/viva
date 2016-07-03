package com.dms.vivanttest.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.dms.vivanttest.R;
import com.dms.vivanttest.data.repository.UserRepositories;
import com.dms.vivanttest.ui.intro.IntroActivity;
import com.dms.vivanttest.ui.photos.PhotosActivity;
import com.dms.vivanttest.ui.base.BaseActivity;
import com.dms.vivanttest.core.User;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        User user = UserRepositories.getInMemoryRepoInstance(this).getUser();
        if(user == null){
            showIntroScreen();
        }else{
            showMainScreen();
        }
    }

    @Override
    public void showMainScreen() {
        startActivity(new Intent(this, PhotosActivity.class));
        finish();
    }

    @Override
    public void showIntroScreen() {
        startActivity(new Intent(this, IntroActivity.class));
        finish();
    }
}

