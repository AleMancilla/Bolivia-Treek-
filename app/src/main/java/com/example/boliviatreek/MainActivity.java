package com.example.boliviatreek;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //private TextView tv_title, tv_ubicacion, tv_kilometraje, tv_distancia, tv_modalidad, tv_dificultad, tv_valoracion, prueba;
    private ListView lv1;

    private String[][] datos_txt = {
            {"texto 1", "texto 1", "texto 1", "texto 1", "texto 1", "texto 1", "texto 1"},
            {"texto 2", "texto 2", "texto 2", "texto 2", "texto 2", "texto 2", "texto 2"},
            {"texto 3", "texto 3", "texto 3", "texto 3", "texto 3", "texto 3", "texto 3"},
            {"texto 4", "texto 4", "texto 4", "texto 4", "texto 4", "texto 4", "texto 4"},
            {"texto 5", "texto 5", "texto 5", "texto 5", "texto 5", "texto 5", "texto 5"},
            {"texto 6", "texto 6", "texto 6", "texto 6", "texto 6", "texto 6", "texto 6"},
            {"texto 7", "texto 7", "texto 7", "texto 7", "texto 7", "texto 7", "texto 7"},
            };
    private int[] datos_img = {R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,};
    private String ubicaciones [] = {"La Paz", "Oruro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView) findViewById(R.id.lv_listaRutas);
        lv1.setAdapter(new Adaptador(this,this.datos_txt,this.datos_img));
    }
}
