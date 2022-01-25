package com.example.walther.gelsaaplication.clases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.walther.gelsaaplication.R;

public class MenuClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_class);
    }

    public void ConsultarPremio(View v){

        Intent intent = new Intent(MenuClass.this,Consultas.class);
        startActivity(intent);
        finish();
    }

    public void CuadreActual(View v){

        Intent intent = new Intent(MenuClass.this,CuadreActual.class);
        startActivity(intent);
        finish();
    }

    public void NumeroGanadores(View v){

        Intent intent = new Intent(MenuClass.this, NumWins.class);
        startActivity(intent);
        finish();
    }
}
