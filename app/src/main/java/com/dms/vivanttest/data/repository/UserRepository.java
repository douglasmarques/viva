/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dms.vivanttest.data.repository;

import android.support.annotation.NonNull;

import com.dms.vivanttest.ui.core.User;

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
