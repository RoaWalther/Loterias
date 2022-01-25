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

import java.util.Scanner;

public class NumWins extends AppCompatActivity {
    private EditText Fecha_numero;
    String  FechaNumJugado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros_ganadores);
    }

    public void FechaNumerosGanadores(View view){

        Fecha_numero= findViewById(R.id.fecha_num_jugado);
        FechaNumJugado = Fecha_numero.getText().toString();
        Scanner lector = new Scanner(System.in);
        String FechaNumeroInvertida = "";
        for (int i = FechaNumJugado.length()-1; i >= 0; i--){
            FechaNumeroInvertida += FechaNumJugado.charAt(i);
        }
        System.out.println("cadena invertida es" + FechaNumeroInvertida);
        Global.TRX_CONSULTAR_NUM_GANADORES = FechaNumJugado;

        System.out.println("CONSULTAR_NUM_ASESOR--------------------------" + Global.TRX_CONSULTAR_NUM_GANADORES);

        if (FechaNumJugado.length()!=0){
            System.out.println("---------------------------------------------------------");
            Messages.packMsgConsultarNumerosGanadores(getApplicationContext());
            Utils.dumpMemory(Global.outputData, Global.outputLen);
            System.out.println("---------------------------------------------------------");
            NumWins.TaskConsultarNumerosGanadores taskNumGanadores = new NumWins.TaskConsultarNumerosGanadores();
            taskNumGanadores.execute();
        }else {
            Toast.makeText(this,"Error indique la fecha jugada ",Toast.LENGTH_SHORT).show();
        }
    }
    /***************************
     Clase       : TaskConsultarNumerosGanadores
     Description : Realiza la transacción de los parámetros
     ***************************/
    public class TaskConsultarNumerosGanadores extends AsyncTask<String, Void, Boolean>{
        ProgressDialog progressDialog;
        /***************************
         Método       : onPreExecute
         Description  : Se ejecuta antes de realizar el proceso
         ***************************/
        protected void onPreExecute (){
            super.onPreExecute();
            progressDialog = new ProgressDialog(NumWins.this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Verificando Numeros Ganadores");
            progressDialog.show();
        }
        /***************************
         Método       : doInBackground
         Description  : Se ejecuta para realizar la transacción y verificar coenxión
         ***************************/
        @Override
        protected Boolean doInBackground(String... values) {
            // Verifica la conexión
            Messages.packMsgConsultarNumerosGanadores(getApplicationContext());// Llama al pack del premio
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

                if (Messages.unPackMsgNumerosGanadores(NumWins.this) == true) {

                    // Dialogo de confirmación
                    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(NumWins.this,
                            R.style.DialogColor));

                    builder.setTitle("Numeros Ganadores");

                    String titulos = "LOT        NUMERO   SERIE\n";
                    String messages = "";

                    for (int i = 0; i < Global.arrayConsultarNumerosGanadores.length;i++)
                        messages += Global.arrayConsultarNumerosGanadores[i].resultado + "\n";

                    builder.setMessage(titulos + messages);
                    builder.setCancelable(false);

                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Finaliza la aplicación
                            ActivityCompat.finishAffinity(NumWins.this);
                            finish();
                            System.exit(0);
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Cancela la salida
                            dialog.cancel();
                        }
                    });

                    Dialog dialog = builder.create();
                    dialog.show();

                    // Cambia el color de la linea horizontal del  AlertDialog
                    Utils.pintarLineaDialogo(getApplicationContext(), dialog);

                }

            } else{
                Toast.makeText(NumWins.this,"No hay conexion con el servidor", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void RegresarAlMenu (View view){
        Intent intent = new Intent( NumWins.this,MenuClass.class);
        startActivity(intent);
    }

    }

