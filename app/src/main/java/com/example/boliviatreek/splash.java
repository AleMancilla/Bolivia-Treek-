package com.example.boliviatreek;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    // declaramos la variable de inicio
    private TextView tv_boliviatrek;
    private Typeface fontcrim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //___________lineas para editar el tipo de fuente_________________
        String dirfontcrim = "fuentes/crimes.ttf";
        this.fontcrim = Typeface.createFromAsset(getAssets(),dirfontcrim);
        tv_boliviatrek = (TextView) findViewById(R.id.idBoliviaTrek);
        tv_boliviatrek.setTypeface(fontcrim);
        //___________lineas para editar el tipo de fuente_________________
        //___________hilo que se modifica en dos segundos_________________
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //cargar la pantalla de inicio
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
        //___________hilo que se modifica en dos segundos_________________
    }
}
