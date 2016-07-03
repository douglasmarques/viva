package com.dms.vivanttest.data.repository;


import android.content.Context;

import com.dms.vivanttest.core.User;
import com.dms.vivanttest.util.Preferences;
import com.dms.vivanttest.util.StringUtils;

public class UserRepositoryImpl implements UserRepository{

    private User user;
    private final Preferences preferences;

    public UserRepositoryImpl(Context context){
        preferences = new Preferences(context);
    }

    public void login(String email, String password, final LoginCallback callback){
        if(TEST_USER.equals(email) && TEST_PASSWORD.equals(password)){
            User user = new User(email);
            saveUser(user);
            callback.onLoginSuccess(user);
        }else{
            callback.onLoginFail(ERROR_WRONG_PASS_OR_USER);
        }
    }

    @Override
    public void saveUser(User user) {
        if(user != null){
            preferences.saveEmail(user.getEmail());
            this.user = user;
        }
    }

    @Override
    public User getUser() {
        if(user == null && StringUtils.isNotBlank(preferences.getEmail())) {
            user = new User(preferences.getEmail());
        }

        return user;
    }

    public void logout(){
        preferences.removeEmail();
        user = null;
    }

}
