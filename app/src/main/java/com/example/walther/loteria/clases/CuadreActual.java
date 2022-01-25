package com.example.walther.gelsaaplication.clases;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.walther.gelsaaplication.R;
import com.example.walther.gelsaaplication.comunes.Global;
import com.example.walther.gelsaaplication.comunes.Messages;
import com.example.walther.gelsaaplication.comunes.TCP;
import com.example.walther.gelsaaplication.comunes.Utils;

public class CuadreActual extends AppCompatActivity {
    String Asesor = "";
    private  EditText  Asesor1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuadre_actual);
    }

    public void ConsultarAsesor (View v){
         Asesor1 = findViewById(R.id.asesor);
         Asesor = Asesor1.getText().toString();
         Global.TRX_CONSULTAR_CUADRE_ACTUAL = Asesor;

        System.out.println("CONSULTAR_NUM_ASESOR--------------------------" + Global.TRX_CONSULTAR_CUADRE_ACTUAL);

        if(Asesor.length() > 0 ){
            System.out.println("---------------------------------------------------------");
            Messages.packMsgConsultarCuadreActual(getApplicationContext());
            Utils.dumpMemory(Global.outputData, Global.outputLen);
            System.out.println("---------------------------------------------------------");
            CuadreActual.TaskConsultarCuadreActual  taskConsultarCuadreActual = new CuadreActual.TaskConsultarCuadreActual();
            taskConsultarCuadreActual.execute();
        }else{
            Toast.makeText(this,"Error Cuadre Actual",Toast.LENGTH_SHORT).show();
        }

    }

    /***************************
     Clase       : TaskAsesor
     Description : Realiza la transacción de los parámetros del Asesor
     ***************************/
    public class TaskConsultarCuadreActual extends AsyncTask<String, Void, Boolean> {
        ProgressDialog progressDialog;

        /***************************
         Método       : onPreExecute
         Description  : Se ejecuta antes de realizar el proceso del Asesor
         ***************************/
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(CuadreActual.this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Cuadre Actual");
            progressDialog.show();
        }

        /***************************
         Método       : doInBackground
         Description  : Se ejecuta para realizar la transacción y verificar conexión
         ***************************/
        @Override
        protected Boolean doInBackground(String... values) {
            // Verifica la conexión
            Messages.packMsgConsultarCuadreActual(getApplicationContext());// Llama al pack del asesor // cambiele a todo esto
            // Verifica la transacción
            if (TCP.transaction(Global.outputLen) == Global.TRANSACTION_OK){
                return true;
            }else{
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

            } else{
                Toast.makeText(CuadreActual.this,"No hay conexion con el servidor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void RegresarAlMenu (View view){
        Intent intent = new Intent( CuadreActual.this,MenuClass.class);
        startActivity(intent);
    }
}
