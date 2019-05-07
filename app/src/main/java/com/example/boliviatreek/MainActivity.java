package com.example.boliviatreek;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // declaramos la variable de inicio
    private TextView tv_boliviatrek;
    private Typeface fontcrim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //___________lineas para editar el tipo de fuente_________________
        String dirfontcrim = "fuentes/crimes.ttf";
        this.fontcrim = Typeface.createFromAsset(getAssets(),dirfontcrim);
        tv_boliviatrek = (TextView) findViewById(R.id.idBoliviaTrek);
        tv_boliviatrek.setTypeface(fontcrim);
        //___________lineas para editar el tipo de fuente_________________
    }
}
