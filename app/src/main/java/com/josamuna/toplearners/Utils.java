package com.josamuna.toplearners;

import android.app.ProgressDialog;
import android.content.Context;

public interface Utils {
    static ProgressDialog getProgressDialog(Context context, String message) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }
}
