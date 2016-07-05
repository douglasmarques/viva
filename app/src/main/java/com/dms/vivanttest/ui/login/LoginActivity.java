package com.dms.vivanttest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.IntentCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.vivanttest.R;
import com.dms.vivanttest.data.repository.UserRepositories;
import com.dms.vivanttest.data.repository.UserRepository;
import com.dms.vivanttest.ui.photos.PhotosActivity;
import com.dms.vivanttest.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Bind(R.id.user_name)
    protected EditText userNameEdt;

    @Bind(R.id.password)
    protected EditText passwordEdt;

    @Bind(R.id.login_progress)
    protected View progressView;

    @Bind(R.id.login_form)
    protected View loginFormView;

    @Bind(R.id.userTil)
    protected TextInputLayout userTil;

    @Bind(R.id.passwordTil)
    protected TextInputLayout passwordTil;

    private LoginContract.UserActionsListener actionsListener;

    private UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dependencyInjection();
        setActionBarTitle(R.string.title_activity_login);
        prepareView();
    }

    private void dependencyInjection() {
        repository = UserRepositories.getInMemoryRepoInstance(this);
        actionsListener = new LoginPresenter(this, repository);
    }

    private void prepareView() {
        //FIXME: just for test
        //userNameEdt.setText(UserRepository.TEST_USER);
        //passwordEdt.setText(UserRepository.TEST_PASSWORD);
        //

        passwordEdt = (EditText) findViewById(R.id.password);
        passwordEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {

                    userNameEdt.setError(null);
                    passwordEdt.setError(null);

                    actionsListener.login(
                            userNameEdt.getText().toString(),
                            passwordEdt.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick(R.id.signIn)
    public void doLogin() {
        userTil.setError(null);
        passwordTil.setError(null);

        actionsListener.login(
                userNameEdt.getText().toString(),
                passwordEdt.getText().toString());
    }

    @Override
    public void showProgress(final boolean show) {
        showProgress(show, loginFormView, progressView);
    }

    @Override
    public void showLoginErrors(LoginPresenter.ValidationLogin validation) {
        switch (validation) {
            case USER_INVALID:
                userTil.setError(getString(validation.mErrorMessage));
                break;
            case PASS_INVALID:
                passwordTil.setError(getString(validation.mErrorMessage));
                break;
            case WRONG_CREDENTIALS:
                Toast.makeText(this, validation.mErrorMessage, Toast.LENGTH_LONG).show();
                break;
            case UNKNOWN_ERROR:
                Toast.makeText(this, validation.mErrorMessage, Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void showMainScreen() {
        Intent intent = new Intent(this, PhotosActivity.class);
        startActivity(IntentCompat.makeRestartActivityTask(intent.getComponent()));
        finish();
    }
}

