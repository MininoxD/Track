package com.example.track;

import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;

public class coneccion {
    public Connection conexionbd(){
        Connection con=null;
        try {
            StrictMode.ThreadPolicy police = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(police);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            //con = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.34;databaseName=registro;user=sa;password=1234");
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://sql5050.site4now.net;databaseName=DB_A54C9E_vitanova;user=DB_A54C9E_vitanova_admin;password=MininoxD1234");
        }catch(Exception e){

        }
        return con;
    }

}
