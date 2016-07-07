package com.dms.viva.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.dms.viva.core.User;
import com.dms.viva.data.repository.UserRepositories;
import com.dms.viva.ui.base.BaseActivity;
import com.dms.viva.ui.photos.PhotosActivity;
import com.dms.viva.R;
import com.dms.viva.ui.intro.IntroActivity;

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

