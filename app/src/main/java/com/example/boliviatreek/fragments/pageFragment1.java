package com.example.boliviatreek.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boliviatreek.Adaptador;
import com.example.boliviatreek.AdapterDatosComentarios;
import com.example.boliviatreek.Comentario;
import com.example.boliviatreek.Product;
import com.example.boliviatreek.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressLint("ValidFragment")
public class pageFragment1 extends Fragment {

    private TextView textView_title_info;
    private TextView textView_descripcion_info;
    private TextView textView_mitos_info;
    private TextView textView_texto_mitos;
    private TextView textView_como_llegar_info;
    private TextView textView_texto_como_llegar;
    private TextView textView_extra_nickname;

    private EditText editText_deja_comentarios;
    private Button button_comentar;
    private FirebaseAuth mAuth;


    private   String fecha_registro_sistema;
    //final String url_perfil;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();




    private List<Comentario> listComentarios;
    Context context;
    private RecyclerView recyclerView;
    private AdapterDatosComentarios adaptor;

    private String id_ruta;

    //@SuppressLint("ValidFragment")
    public pageFragment1(String id_ruta, Context context) {
        this.id_ruta=id_ruta;
        this.context=context;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


         ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.view_pager_1,container
                        ,false);

        listComentarios = new ArrayList<>();
        adaptor=new AdapterDatosComentarios(context,listComentarios);





        mAuth = FirebaseAuth.getInstance();

        Time fecha_registro = new Time(Time.getCurrentTimezone());
        fecha_registro.setToNow();
        int idia = fecha_registro.monthDay;
        int imes= fecha_registro.month+1;
        int iyear = fecha_registro.year;

        fecha_registro_sistema= idia + "/" + imes + "/" + iyear;
        //llenarComentarios(fecha_registro_sistema);

        recyclerView = rootView.findViewById(R.id.ReciclerComentarios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adaptor);
//
//        recyclerView = rootView.findViewById(R.id.ReciclerComentarios);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
//        listComentarios = new ArrayList<String>();
//        for (int i = 0; i < 10; i++) {
//            listComentarios.add("Dato = "+i);
//        }
//        AdapterDatosComentarios adapter = new AdapterDatosComentarios(listComentarios);
//        recyclerView.setAdapter(adapter);
//



        textView_title_info = rootView.findViewById(R.id.textView_title_info);

        textView_descripcion_info = rootView.findViewById(R.id.textView_descripcion_info);
        textView_mitos_info = rootView.findViewById(R.id.textView_mitos_info);
        textView_texto_mitos = rootView.findViewById(R.id.textView_texto_mitos);
        textView_como_llegar_info = rootView.findViewById(R.id.textView_como_llegar_info);
        textView_texto_como_llegar = rootView.findViewById(R.id.textView_texto_como_llegar);
        textView_extra_nickname = rootView.findViewById(R.id.textView_extra_nickname);

        editText_deja_comentarios = rootView.findViewById(R.id.editText_deja_comentario);
        button_comentar = rootView.findViewById(R.id.button_comentar);




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



        button_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comentar(v);
            }
        });


        /////////////////////////////////////////////

        db.collection("Comentarios")
                .whereEqualTo("ID_RUTA",id_ruta)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null)
                {
                    //error
                }
                for (DocumentChange doc: queryDocumentSnapshots.getDocumentChanges())
                {
                    if (doc.getType()== DocumentChange.Type.ADDED)
                    {
                        String id = doc.getDocument().getId();
                        //Log.d("PRUEBA______", "onEvent: "+name);
                        Comentario users = doc.getDocument().toObject(Comentario.class).whitId(doc.getDocument().getId());
                        listComentarios.add(users);
//                        Log.d("PRUEBA______", "onEvent: "+users.getNicknameuser());
                        adaptor.notifyDataSetChanged();
                    }


                }
            }
        });

        /////////////////////////////////////////////

        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
        //
    }



    public void comentar (View v)
    {

        final String comentario_caja = editText_deja_comentarios.getText().toString();

        final String[] nickname = new String[1];
        String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        //////////////////////////////////////////
        db.collection("Usuarios").whereEqualTo("Email",user)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {


//                                Log.d(TAG, document.getId() + " => " + document.getData());
                                nickname[0] = document.getData().get("nickname").toString();

                                textView_extra_nickname.setText(nickname[0]);
                                Toast.makeText(context, "Se agrego el comentario correctamente " , Toast.LENGTH_SHORT).show();
                                String url_perfil=document.getData().get("url_perfil").toString();
                                comentarioenbd(nickname,comentario_caja,url_perfil);
                                editText_deja_comentarios.setText("");


                                //listComentarios.add(new Comentario( url_perfil,  nickname,  comentario,  fecha_registro_sistema));
                            }
                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        ///////////////////////////////////////////


    }
    public void comentarioenbd(final String[] nickname, final String comentario_caja,String url_perfil)
    {
        // Create a new user with a first, middle, and last name
        final Map<String, Object> comentario = new HashMap<>();
        comentario.put("nickname", nickname[0]);
        comentario.put("fecha_registro",    fecha_registro_sistema);
        comentario.put("comentario", comentario_caja);
        comentario.put("ID_RUTA",id_ruta);
        comentario.put("ID_COMENTARIO","______");
        comentario.put("url_perfil",url_perfil);

        //        comentario.put("born", 1912);

// Add a new document with a generated ID
        db.collection("Comentarios")
                .add(comentario)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        //listComentarios.add(new Comentario( textView_extra_nickname.getText().toString(),  nickname[0],  comentario_caja,  fecha_registro_sistema));
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        ActualizarDatos(documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void ActualizarDatos( String id)
    {
        db.collection("Comentarios").document(id)
                .update(
                        "ID_COMENTARIO", id
                );
    }




}
