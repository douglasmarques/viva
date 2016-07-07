package com.dms.viva.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Preferences {

    private final Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public String getEmail() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("email", null);
    }

    public void saveEmail(String email) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putString("email", email);
        editor.apply();
    }

    public void removeEmail() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.remove("email");
        editor.apply();
    }

}
