package com.example.track;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
class Registrado {
    private Activity ac;
    private AlertDialog alert;
    Registrado(Activity myActivity){
        ac=myActivity;
    }

    void  startAlertRegis(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ac);
        LayoutInflater inflater = ac.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_acess, null));
        builder.setCancelable(true);

        alert = builder.create();
        alert.show();
    }

    void  startAlertError(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ac);
        LayoutInflater inflater = ac.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_error, null));
        builder.setCancelable(true);

        alert = builder.create();
        alert.show();
    }

    void  startAlertNovalido(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ac);
        LayoutInflater inflater = ac.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_novalido, null));
        builder.setCancelable(true);

        alert = builder.create();
        alert.show();
    }
    void dismiss(){
        alert.dismiss();
    }
}
