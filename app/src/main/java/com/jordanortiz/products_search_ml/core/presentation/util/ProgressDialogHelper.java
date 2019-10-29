package com.jordanortiz.products_search_ml.core.presentation.util;

import android.app.AlertDialog;
import android.content.Context;

import com.jordanortiz.products_search_ml.R;
//import dmax.dialog.SpotsDialog;

public class ProgressDialogHelper {
    private AlertDialog dialog;

    private volatile int progressesCount = 0;

    public void showProgress(Context context) {
        showProgress(context, null);
    }

    public void showProgress(Context context, String message) {
        showProgress(context, message, null);
    }

    public void showProgress(Context context, String message, String title) {
        if (context == null) {
            return;
        }

        if (!inProgress()) {
//            dialog = new SpotsDialog.Builder()
//                    .setContext(context)
//                    .setMessage(message)
//                    .setTheme(R.style.CustomProgressDialog)
//                    .setCancelable(false)
//                    .build();
//            dialog.show();
        }

        progressesCount++;
    }

    public void hideProgress() {
        progressesCount--;
        if (progressesCount <= 0) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            progressesCount = 0;
        }

    }

    private boolean inProgress() {
        return dialog != null && dialog.isShowing();
    }
}
