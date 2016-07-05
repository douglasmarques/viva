package com.dms.vivanttest.ui.login;


import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.dms.vivanttest.R;
import com.dms.vivanttest.core.User;
import com.dms.vivanttest.data.repository.UserRepository;

import static com.dms.vivanttest.ui.login.LoginPresenter.ValidationLogin.UNKNOWN_ERROR;
import static com.dms.vivanttest.ui.login.LoginPresenter.ValidationLogin.WRONG_CREDENTIALS;


/**
 * Listens to user actions from the UI ({@link LoginActivity}), retrieves the data and updates
 * the UI as required.
 */
public class LoginPresenter implements LoginContract.UserActionsListener {

    enum ValidationLogin {

        USER_INVALID(R.string.error_invalid_email),
        PASS_INVALID(R.string.error_invalid_password),
        UNKNOWN_ERROR(R.string.error_unknown_error),
        WRONG_CREDENTIALS(R.string.error_wrong_credentials);

        public final int mErrorMessage;

        ValidationLogin(@StringRes int errorMessage) {
            mErrorMessage = errorMessage;
        }
    }

    @NonNull
    private final LoginContract.View mLoginView;

    @NonNull
    private final UserRepository mRepository;

    LoginPresenter(@NonNull LoginContract.View loginView,
                   @NonNull UserRepository repository) {
        mLoginView = loginView;
        mRepository = repository;
    }


    @Override
    public void login(String email, String pass) {

        mLoginView.showProgress(true);

        //if (email.isEmpty() || !Validator.isValidEmail(email)) {
        if (email.isEmpty() || !email.contains("@")) {
            mLoginView.showProgress(false);
            mLoginView.showLoginErrors(ValidationLogin.USER_INVALID);
        } else if (pass.isEmpty() || pass.length() < 4) {
            mLoginView.showProgress(false);
            mLoginView.showLoginErrors(ValidationLogin.PASS_INVALID);
        } else {
            mRepository.login(email, pass, new UserRepository.LoginCallback() {
                @Override
                public void onLoginSuccess(User user) {
                    mLoginView.showMainScreen();
                }

                @Override
                public void onLoginFail(int error) {
                    mLoginView.showProgress(false);
                    if (error == UserRepository.ERROR_WRONG_PASS_OR_USER) {
                        mLoginView.showLoginErrors(WRONG_CREDENTIALS);
                    } else {
                        mLoginView.showLoginErrors(UNKNOWN_ERROR);
                    }
                }
            });
        }
    }

}
