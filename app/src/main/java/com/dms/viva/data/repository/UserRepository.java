package com.dms.viva.data.repository;

import android.support.annotation.NonNull;

import com.dms.viva.core.User;

/**
 * Main entry point for accessing user data.
 */
public interface UserRepository {

    int ERROR_WRONG_PASS_OR_USER = 1;

    String TEST_USER = "test@test.com";
    String TEST_PASSWORD = "test1234";

    interface LoginCallback {

        void onLoginSuccess(User user);

        void onLoginFail(int error);
    }


    void login(@NonNull String user, @NonNull String password, LoginCallback callback);

    void saveUser(User user);

    User getUser();

    void logout();

}
