package com.dms.viva.ui.widget;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.dms.viva.util.StringUtils;


public class Alert {

    public static void showMessage(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(false);
        if (!StringUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        try {
            builder.show();
        } catch (WindowManager.BadTokenException ex) {
            //activity is not visible
        }
    }

    public static void showMessage(Context context, String message) {
        showMessage(context, null, message);
    }

    public static void showConfirm(Context context, String message, String positive, String negative, DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(positive, positiveListener);
        builder.setNegativeButton(negative, null);
        try {
            builder.show();
        } catch (WindowManager.BadTokenException ex) {
            //activity is not visible
        }
    }
}
