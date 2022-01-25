package com.example.walther.gelsaaplication.clases;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.walther.gelsaaplication.R;
import com.example.walther.gelsaaplication.comunes.Global;
import com.example.walther.gelsaaplication.comunes.Messages;
import com.example.walther.gelsaaplication.comunes.TCP;
import com.example.walther.gelsaaplication.comunes.Utils;

public class Consultas extends AppCompatActivity {
    private EditText serie1,serie2;
    String colilla = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);


    }

    public void consultar (View v){

        serie1 = findViewById(R.id.serie1);
        serie2 = findViewById(R.id.serie2);
        //odtengo los parametros ingresados por el usuario
        colilla = serie1.getText().toString()+ serie2.getText().toString();

        Global.TRX_CONSULTAR_PREMIO = colilla;

        System.out.println("VALOR GLOBAL COLILLAS --------------------------" + Global.TRX_CONSULTAR_PREMIO);
        //valido que los campos deban ser devidamente llenados por el usuario para proseguir a realizar la transacción
        if(colilla.length() > 0 ){
            System.out.println("---------------------------------------------------------");
            Messages.packMsgConsultarPremio(getApplicationContext());
            Utils.dumpMemory(Global.outputData, Global.outputLen);
            System.out.println("---------------------------------------------------------");
            Consultas.TaskConsultarPremio taskConsultarPremio = new Consultas.TaskConsultarPremio();
            taskConsultarPremio.execute();
        }else{
            Toast.makeText(this,"Error verifique los campos de la colilla",Toast.LENGTH_SHORT).show();
        }

    }

    /***************************
     Clase       : TaskConsultarPremio
     Description : Realiza la transacción de consultar premio
     ***************************/
    public class TaskConsultarPremio extends AsyncTask<String, Void, Boolean> {
        ProgressDialog progressDialog;

        /***************************
         Método       : onPreExecute
         Description  : Se ejecuta antes de realizar el proceso de la colilla // aca
         ***************************/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Consultas.this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Consultando Premio");
            progressDialog.show();
        }

        /***************************
         Método       : doInBackground
         Description  : Se ejecuta para realizar la transacción y verificar coenxión
         ***************************/
        @Override
        protected Boolean doInBackground(String... values) {
            // Verifica la conexión
            Messages.packMsgConsultarPremio(getApplicationContext());// Llama al pack del premio
            // Verifica la transacción
            if (TCP.transaction(Global.outputLen) == Global.TRANSACTION_OK) {
                return true;
            } else {
                return false;
            }
        }

        /***************************
         Método       : onPostExecute
         Description  : Se ejecuta después de realizar el doInBackground
         ***************************/
        @Override
        protected void onPostExecute(Boolean value) {

            progressDialog.dismiss();
            TCP.disconnect();

            if (value == true) {


                if (Messages.unPackMsgConsultarPremio(Consultas.this) == true) {

                }

            } else{
                Toast.makeText(Consultas.this,"No hay conexion con el servidor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void RegresarAlMenu (View view){
        Intent intent = new Intent( Consultas.this,MenuClass.class);
        startActivity(intent);
    }
}

