package com.example.track;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

class LoadingDialog {
    private Activity ac;
    private AlertDialog alert;

    LoadingDialog(Activity myActivity){
        ac = myActivity;
    }

    void  startAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ac);
        LayoutInflater inflater = ac.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_load, null));
        builder.setCancelable(false);

        alert = builder.create();
        alert.show();
    }

    void dismiss(){
        alert.dismiss();
    }
}
