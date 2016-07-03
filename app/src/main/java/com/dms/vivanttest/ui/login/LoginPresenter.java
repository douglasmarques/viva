package com.dms.vivanttest.ui.login;


import android.support.annotation.StringRes;

import com.dms.vivanttest.R;


/**
 * Listens to user actions from the UI ({@link LoginActivity}), retrieves the data and updates
 * the UI as required.
 */
public class LoginPresenter implements LoginContract.UserActionsListener {

    public enum ValidationLogin {

        USER_INVALID(R.string.error_invalid_email),
        PASS_INVALID(R.string.error_invalid_password);

        public final int mErrorMessage;

        ValidationLogin(@StringRes int errorMessage){
            mErrorMessage = errorMessage;
        }
    }

//    @NonNull
//    private final LoginContract.View mLoginView;

//    @NonNull
//    private final UserRepository mRepository;
//
//    public LoginPresenter(@NonNull LoginContract.View loginView,
//                          @NonNull UserRepository repository){
//        mLoginView = loginView;
//        mRepository = repository;
//    }


    @Override
    public void login(String email, String pass) {

        //mLoginView.showProgress(true);

//        if(email.isEmpty() || !Validator.isValidEmail(email)){
//            mLoginView.showInvalidFieldErrors(ValidationLogin.USER_INVALID);
//        }else if(pass.isEmpty() || pass.length() < 4) {
//            mLoginView.showInvalidFieldErrors(ValidationLogin.PASS_INVALID);
//        }else{
//            mLoginView.showProgress(true);
//            mRepository.login(email, pass, new UserRepository.LoginCallback() {
//                @Override
//                public void onLoginSuccess(User user) {
//                    mLoginView.showMainScreen();
//                }
//
//                @Override
//                public void onLoginFail(String error) {
//                    mLoginView.showProgress(false);
//                    mLoginView.showLoginError(error);
//                }
//            });
//        }
    }

}
