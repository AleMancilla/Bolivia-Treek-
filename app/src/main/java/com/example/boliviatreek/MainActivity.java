package com.example.boliviatreek;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //private TextView tv_title, tv_ubicacion, tv_kilometraje, tv_distancia, tv_modalidad, tv_dificultad, tv_valoracion, prueba;
    private ListView lv1;
    private TabHost th;

    // para la base de datos
    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    private String[][] datos_txt2 = {{"uno","uno","uno","uno","uno","uno","uno"},
            {"uno","uno","uno","uno","uno","uno","uno"},
            {"dos","uno","dos","uno","uno","uno","uno"},
            {"uno","uno","uno","uno","uno","uno","uno"},
            {"uno","uno","uno","uno","uno","uno","uno"},
            {"uno","uno","uno","uno","uno","uno","uno"},
            {"uno","uno","uno","uno","uno","uno","uno"}} ;
    //private String[][] datos_txt;
    private int[] datos_img2 = {R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,};


    private String ubicaciones [] = {"La Paz", "Oruro"};


    public int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //database
        //int x = db.collection("Rutas")
        db.collection("Rutas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult())
                            {

                                contador++;

                                Log.d("***PRUEBA CONTADOR ***", document.getId() + " => " + document.getData());
                            }
                            funcionAux(contador);

                        } else {
                            //Log.w("", "Error getting documents.", task.getException());
                        }
                    }
                });
        //contador++;
        //Toast.makeText(MainActivity.this, "CONTADOR = "+contador, Toast.LENGTH_SHORT).show();

        //lv1 = (ListView) findViewById(R.id.lv_listaRutas);
        //lv1.setAdapter(new Adaptador(this,this.datos_txt2,this.datos_img));
        //th =(TabHost) findViewById(R.id.th_principal);

        // ___________ inicia la configuracion de TABHOST____________//

        th =(TabHost) findViewById(R.id.th_principal);

        lv1 = (ListView) findViewById(R.id.lv_listaRutas);
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


    public void funcionAux (final int conti)
    {

        db.collection("Rutas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    private String[][] datos_txt = new String[conti][7];
                    private int[] datos_img = {R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,R.mipmap.icontrek,};


                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            int i=0;

                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                datos_txt[i][0] = document.getData().get("txt1").toString();
                                datos_txt[i][1] = document.getData().get("txt2").toString();
                                datos_txt[i][2] = document.getData().get("txt3").toString();
                                datos_txt[i][3] = document.getData().get("txt4").toString();
                                datos_txt[i][4] = document.getData().get("txt5").toString();
                                datos_txt[i][5] = document.getData().get("txt6").toString();
                                datos_txt[i][6] = document.getData().get("txt7").toString();
                                i++;

                            }
                            lv1.setAdapter(new Adaptador(getApplication(),datos_txt,this.datos_img));
                        } else {
                            //Log.w("", "Error getting documents.", task.getException()); document.getData()
                        }
                    }
                });

        //lv1.setAdapter(new Adaptador(getApplication(),datos_txt2,this.datos_img2));
    }
}
