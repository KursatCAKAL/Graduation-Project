package com.example.kc.gpdriverless;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class ToastUtils extends Application {

    private static ToastUtils mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static void showToast(String data) {
        Toast.makeText(mContext, data,Toast.LENGTH_SHORT).show();
    }

    public static void showAlert(Activity activity) {


        new AlertDialog.Builder(activity)
                .setTitle("Sil")
                .setMessage("Silmek istedigine eminmisin?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
