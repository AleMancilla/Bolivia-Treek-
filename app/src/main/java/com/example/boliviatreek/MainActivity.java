package com.example.boliviatreek;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView lv1;
    private TabHost th;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private Button button_cerrar_sesion;

    private FirebaseFirestore db;
    private EditText edt_titleRuta;
    private EditText edt_kilometraje;
    private EditText edt_ubicacion;
    private EditText edt_valoracion;
    private Button btn_SubirImagen;
    private Button btn_registrarRuta;
    private ImageView imgv_imagenRuta;
    private static final int GALLERY_INTENT = 1;
    private ProgressDialog progressDialog;
    private StorageReference mStorageRef;
    private TextView tv_url;
    private ProgressDialog progressDialog2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=FirebaseFirestore.getInstance();
        edt_kilometraje=findViewById(R.id.editText_kilometraje);
        edt_titleRuta=findViewById(R.id.editText_titleRuta);
        edt_ubicacion=findViewById(R.id.editText_ubicacion);
        edt_valoracion=findViewById(R.id.editText_valoracion);
        btn_SubirImagen = findViewById(R.id.button_SubirImagen);
        btn_registrarRuta=findViewById(R.id.button_registrarRuta);
        imgv_imagenRuta = findViewById(R.id.imageView_imgruta);
        progressDialog = new ProgressDialog(this);
        tv_url=findViewById(R.id.editText_url);
        progressDialog2 = new ProgressDialog(this);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        ////////////////////////////////////////////
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        lv1 = findViewById(R.id.lv_listaRutas);
        button_cerrar_sesion = findViewById(R.id.button_cerrar_sesion);

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

    public void cerrarSesion(View v)
    {
        firebaseAuth.getInstance().signOut();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    public void subirImagenBackground (View v)
    {
        //para llamar una imagen de la galeria
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,GALLERY_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("cargando imagen del storage ");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //Log.d("___PRUEBA__", "HOLA MUNDO __________________________***");

        //para no crashear si toma foto o selecciona imagen
       if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null)
        {   //de parte de firebases
            Uri uri = data.getData();
            //Glide.with(MainActivity.this)
            //        .load(uri)
            //        .fitCenter()
            //        .centerCrop().into(imgv_imagenRuta);
            //tv_url.setText(uri.toString());
            progressDialog.dismiss();

            ////String[] projection = {MediaStore.Images.Media.DATA};

            ////Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            ////cursor.moveToFirst();

            ////int columnIndex = cursor.getColumnIndex(projection[0]);
            ////String path = cursor.getString(columnIndex);
            ////Log.d("___PRUEBA__", "hallando ryta __________________________***"+path);
            ////cursor.close();


            final StorageReference filepath = mStorageRef.child("fotos").child(uri.getLastPathSegment());

            Log.d("___PRUEBA__", "_______**___*____"+uri.getLastPathSegment());

            //Glide.with(MainActivity.this)
              //      .load(uri)
                //    .fitCenter()
                  //  .centerCrop().into(imgv_imagenRuta);
            //tv_url.setText(uri.toString());

            ////uri = Uri.fromFile(new File(path));
            ////StorageReference riversRef = mStorageRef.child("fotos");

            filepath.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this, "Se subio exitosa la foto", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();


                    //para sacar la url de la imagen cargada
                    Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String photoLink = uri.toString();
                            Log.d("ESTE __________MENSAJE ", "URL__________: "+photoLink);

                            //codigo extra editar list view
                            Glide.with(MainActivity.this)
                                    .load(photoLink)
                                    .fitCenter()
                                    .centerCrop().into(imgv_imagenRuta);
                            tv_url.setText(photoLink);
                            //codigo extra

                        }
                    });
                    //fin de codigo que saca url

                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "ERROR AL SUBIR", Toast.LENGTH_SHORT).show();
                    Log.d("___PRUEBA__", "__________*____ ERROR AL SUBIR");

                }
            });

        }
    }

    //////////////////////////////////////////////
    public void registrarRuta(View v)
    {
        // el trim es para eliminar espacios que tengamos al principio y al final
        final String title = edt_titleRuta.getText().toString().trim();
        final String ubicacion = edt_ubicacion.getText().toString().trim();
        final String valoracion = edt_valoracion.getText().toString();
        final String kilometraje = edt_kilometraje.getText().toString();
        final String urlbackground = tv_url.getText().toString();

        // para la base de datos
        final Map<String, Object> userdb = new HashMap<>();


        //creamos a una nueva ruta

                            // para la base de datos
        userdb.put("title",title);
        userdb.put("ubicacion", ubicacion);
        userdb.put("valoracion",valoracion);
        userdb.put("kilometraje",kilometraje);
        userdb.put("backgroundUrl", urlbackground);
        userdb.put("dificultad","dificultad");
        userdb.put("distancia","distancia");
        userdb.put("iconUrl","https://firebasestorage.googleapis.com/v0/b/boliviatreek.appspot.com/o/BoliviaTrekRutas%2Fdescarga.png?alt=media&token=3ddde83a-f787-4c7a-bdef-9c09326ee6c1");
        userdb.put("modalidad","Modo Fuerte");
        userdb.put("nicknameuser", user.getEmail());
        Log.d(" MENSAJE _____*_", "user.getEmail() "+user.getEmail());



        db.collection("Rutas")
                .add(userdb)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Datos agregados correctamente a la base de datos", Toast.LENGTH_SHORT).show();
                        Log.d(" MENSAJE ____error_", "entro ");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // en caso de que falle
                        Log.d(" MENSAJE ____error_", "no entro ");
                    }
                });
        //


    }
}


