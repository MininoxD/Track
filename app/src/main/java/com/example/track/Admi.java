package com.example.track;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admi extends AppCompatActivity {
    private EditText dni;
    private EditText nombre;
    private EditText cel;
    private EditText direc;
    private EditText ref;
    private EditText descri;
    private Button regis;

    coneccion co= new coneccion();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admi);

        dni = (EditText)findViewById(R.id.txtDNIR);
        nombre =(EditText)findViewById(R.id.txtNR);
        cel = (EditText)findViewById(R.id.txtCEL);
        direc = (EditText)findViewById(R.id.txtDIRE);
        ref = (EditText)findViewById(R.id.txtREF);
        descri = (EditText)findViewById(R.id.txtDESCR);

        regis = (Button)findViewById(R.id.btnREGIS);
    }

    public void registrar(View view){
        if(dni.getText().toString().length() >8  && nombre.getText().toString().length() >1 && cel.getText().toString().length()==9 &&
                direc.getText().toString().length()>8 && ref.getText().toString().length() >8 && descri.getText().toString().length()>5){

            String query ="insert into tiendas values(?,?,?,?,?,?);";
            try {
                PreparedStatement rs = co.conexionbd().prepareStatement(query);
                rs.setString(1,dni.getText().toString());
                rs.setString(2,nombre.getText().toString());
                rs.setString(3,cel.getText().toString());
                rs.setString(4,direc.getText().toString());
                rs.setString(5,ref.getText().toString());
                rs.setString(6,descri.getText().toString());
                rs.executeUpdate();
                rs.close();
                //Toast.makeText(getApplicationContext(),"Se Registro Su visita",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Resultado");
                builder.setMessage("Se Registro una nueva tienda");
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }catch (SQLException e){
                //Toast.makeText(getApplicationContext(),"No se registro la visita: "+e.getMessage(),Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alerta");
                builder.setMessage("Nose registro por: "+e.getMessage());
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("ALERTA");
            builder.setMessage("DEVE RELLENAR TODOS LOS CAMPOS");
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }
}
