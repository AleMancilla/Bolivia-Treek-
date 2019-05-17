package com.example.boliviatreek;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //private TextView tv_title, tv_ubicacion, tv_kilometraje, tv_distancia, tv_modalidad, tv_dificultad, tv_valoracion, prueba;
    private ListView lv1;
    private TabHost th;
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
        th =(TabHost) findViewById(R.id.th_principal);

        // ___________ inicia la configuracion de TABHOST____________//
        //***tab1
        th.setup(); //configuracion
        TabHost.TabSpec ts1 = th.newTabSpec("TabSpec1"); //se establece primero un tabspec relacionado con el tahost , el string es para identificarlo
        ts1.setIndicator("RUTAS");//texto que se mostrara
        ts1.setContent(R.id.tab1); // contenido

        th.addTab(ts1);
        //***
        //***tab2
        th.setup(); //configuracion
        TabHost.TabSpec ts2 = th.newTabSpec("TabSpec2"); //se establece primero un tabspec relacionado con el tahost , el string es para identificarlo
        ts2.setIndicator("PAQUETE TURISTICO");//texto que se mostrara
        ts2.setContent(R.id.tab2); // contenido

        th.addTab(ts2);
        //***
        //***tab3
        th.setup(); //configuracion
        TabHost.TabSpec ts3 = th.newTabSpec("TabSpec3"); //se establece primero un tabspec relacionado con el tahost , el string es para identificarlo
        ts3.setIndicator("AGREGA TU RUTA");//texto que se mostrara
        ts3.setContent(R.id.tab3); // contenido

        th.addTab(ts3);
        //***
    }
}
