package com.dms.vivanttest.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.vivanttest.R;
import com.dms.vivanttest.data.repository.UserRepositories;
import com.dms.vivanttest.ui.IntroActivity;
import com.dms.vivanttest.ui.MainActivity;
import com.dms.vivanttest.ui.base.BaseActivity;
import com.dms.vivanttest.ui.core.User;
import com.dms.vivanttest.ui.login.LoginContract;
import com.dms.vivanttest.ui.login.LoginPresenter;

import butterknife.Bind;
import butterknife.OnClick;

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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showIntroScreen() {
        startActivity(new Intent(this, IntroActivity.class));
        finish();
    }
}

