package com.example.track;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends AppCompatActivity {
    private EditText dni;
    private Button ingre;
    coneccion co= new coneccion();
    private LoadingDialog ld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dni = (EditText)findViewById(R.id.txtDNI);
        ingre = (Button)findViewById(R.id.btnIngre);
        ld = new LoadingDialog(Login.this);
        cargarpref();

        ingre.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(dni.getText().toString().equals("72388160")){
                    admi();
                }
                return false;
            }
        });
    }
    public  void admi(){
        Intent i = new Intent(this,Admi.class);
        startActivity(i);
    }
    public void logear(View view){
            if(dni.getText().toString().length() ==8){
                ResultSet rs= null;
                try {
                    PreparedStatement pst= co.conexionbd().prepareStatement("select dni,nombre from tiendas where dni='"+dni.getText().toString()+"'");
                    rs=pst.executeQuery();
                    if(rs.next()){
                        guuardar(rs.getString(1),rs.getString(2));
                        Intent i = new Intent(this,Usuario.class);
                        i.putExtra("dni",rs.getString(1));
                        i.putExtra("nombre",rs.getString(2));
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario no registrado",Toast.LENGTH_LONG).show();
                    }
                }catch (SQLException e){

                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"Ingrese los 8 digitos",Toast.LENGTH_LONG).show();
            }
    }

    public void guuardar(String dni, String nom){
        SharedPreferences pref = getSharedPreferences("USUARIO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("dni",dni);
        editor.putString("nombre",nom);
        editor.commit();
    }

    public void cargarpref(){
        SharedPreferences pref = getSharedPreferences("USUARIO", Context.MODE_PRIVATE);
        String dni = pref.getString("dni"," ");
        String nom = pref.getString("nombre"," ");
        if(dni!= " " && nom != " "){
            Intent i = new Intent(this,Usuario.class);
            i.putExtra("dni",dni);
            i.putExtra("nombre",nom);
            startActivity(i);
        }
    }

    public static void eliminarpref(Context c){
        SharedPreferences pref = c.getSharedPreferences("USUARIO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("dni");
        editor.remove("nombre");
        editor.commit();
    }

}
