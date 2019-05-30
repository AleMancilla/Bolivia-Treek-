package com.example.boliviatreek;

import android.app.Activity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv1;
    private TabHost th;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////////////////////////////////////////////
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        lv1 = findViewById(R.id.lv_listaRutas);

        db.collection("Rutas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        List<Product> mProductList = new ArrayList<>();
                        if(task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {

                                Log.d("*****prueba****", "Document.getData: " + document.getData());

                                Product miss = document.toObject(Product.class);

                                Log.d("*****prueba****", "Document.toObject(Product.class): " + miss.tostring());

                                mProductList.add(miss);
                            }
                            Adaptador mProductAdapter = new Adaptador(MainActivity.this, (ArrayList<Product>) mProductList);
                            mProductAdapter.notifyDataSetChanged();
                            Log.d("_________prueba________", "Document.toObject(Product.class): " + mProductAdapter);
                            lv1.setAdapter(mProductAdapter);
                        }
                    }
                });


        ////////////////////////////////////////////////




        th =(TabHost) findViewById(R.id.th_principal);

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


    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.getInstance().signOut();
    }
}
