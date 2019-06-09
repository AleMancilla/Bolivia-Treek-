package com.example.boliviatreek.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boliviatreek.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



@SuppressLint("ValidFragment")
public class pageFragment1 extends Fragment {

    private TextView textView_title_info;
    private TextView textView_descripcion_info;
    private TextView textView_mitos_info;
    private TextView textView_texto_mitos;
    private TextView textView_como_llegar_info;
    private TextView textView_texto_como_llegar;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();;



    private String id_ruta;

    //@SuppressLint("ValidFragment")
    public pageFragment1(String id_ruta) {
        this.id_ruta=id_ruta;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



         ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.view_pager_1,container
                        ,false);


        textView_title_info = rootView.findViewById(R.id.textView_title_info);

        textView_descripcion_info = rootView.findViewById(R.id.textView_descripcion_info);
        textView_mitos_info = rootView.findViewById(R.id.textView_mitos_info);
        textView_texto_mitos = rootView.findViewById(R.id.textView_texto_mitos);
        textView_como_llegar_info = rootView.findViewById(R.id.textView_como_llegar_info);
        textView_texto_como_llegar = rootView.findViewById(R.id.textView_texto_como_llegar);




        //textView_title_info.setText(id_ruta);

        textView_mitos_info.setText("Mitos y Leyendas");
        textView_como_llegar_info.setText("Como llegar?");

        db.collection("Rutas").whereEqualTo("ID RUTAS",id_ruta)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());

                                String title = document.getData().get("title").toString();
                                String descripcion_info = document.getData().get("info").toString();
                                String mitos = document.getData().get("mitos").toString();
                                String como_llegar = document.getData().get("como llegar").toString();

                                textView_title_info.setText(title);
                                textView_descripcion_info.setText(descripcion_info);
                                textView_texto_mitos.setText(mitos);
                                textView_texto_como_llegar.setText(como_llegar);



                            }
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });




        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
        //
    }


}
