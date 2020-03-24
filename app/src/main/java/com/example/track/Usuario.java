package com.example.track;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuario extends AppCompatActivity {
    private Button salida;
    private Button scaner;
    private TextView nombre;
    String dni,nom;
    coneccion co= new coneccion();
    private Registrado re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        salida= (Button)findViewById(R.id.btnSalir);
        scaner= (Button)findViewById(R.id.btnScanear);
        nombre = (TextView)findViewById(R.id.txtNombre);

        nom = getIntent().getStringExtra("nombre");
        dni = getIntent().getStringExtra("dni");
         re = new Registrado(Usuario.this);
        nombre.setText('"'+nom+'"');
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result != null){
            if (result.getContents() != null){
                if(isNumeric(result.getContents()) && result.getContents().length() == 8){
                    registrar(Integer.parseInt(result.getContents()));
                }else{
                    re.startAlertNovalido();
                }
            }else{
                re.startAlertError();
            }
        }
    }
    public void registrar(int id){
        String query ="insert into visitas(dni_t,dni) values(?,?);";
        try {
            PreparedStatement rs = co.conexionbd().prepareStatement(query);
            rs.setString(1,dni);
            rs.setInt(2,id);
            rs.executeUpdate();
            rs.close();
            re.startAlertRegis();
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),"No se registro la visita: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    public void scaanear(View view){
        //new IntentIntegrator(Usuario.this).initiateScan();
        IntentIntegrator sca = new IntentIntegrator(Usuario.this);
        sca.setPrompt("Escanee el codigo QR del lugar donde se ecuentra");
        sca.setOrientationLocked(false);
        sca.setCaptureActivity(CaptureActivityPortrait.class);
        sca.initiateScan();
    }

    public void cerrar(View view){
        Login.eliminarpref(Usuario.this);
        Intent i = new Intent(this,Login.class);
        startActivity(i);
        finish();
    }
}
