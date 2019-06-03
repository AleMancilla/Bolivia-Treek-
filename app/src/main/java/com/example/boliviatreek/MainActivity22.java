package com.example.boliviatreek;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.boliviatreek.fragments.pageFragment1;
import com.example.boliviatreek.fragments.pageFragment2;
import com.example.boliviatreek.fragments.pageFragment3;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity22 extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private String id_ruta;
    private Button button_albergues;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView imageView_principal;

    private TextView textView_TITLE_PRINCIPAL;
    private TextView textView_UBICACION_PRINCIPAL;
    private TextView textView_VALORACION_PRINCIPAL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        id_ruta=getIntent().getStringExtra("ID");
        button_albergues=findViewById(R.id.button_albergues);
        imageView_principal=findViewById(R.id.imageView_PRINCIPAL);

        textView_TITLE_PRINCIPAL=findViewById(R.id.textView_TITLE_PRINCIPAL);
        textView_UBICACION_PRINCIPAL=findViewById(R.id.textView_UBICACION_PRINCIPAL);
        textView_VALORACION_PRINCIPAL=findViewById(R.id.textView_VALORACION_PRINCIPAL);

        List<Fragment> list = new ArrayList<>();
        list.add(new pageFragment1());
        list.add(new pageFragment2());
        list.add(new pageFragment3());

        pager = findViewById(R.id.id_ViewPager);
        pagerAdapter = new SliderPageAdapter(getSupportFragmentManager(),list);

        pager.setAdapter(pagerAdapter);


        ////////////////////base de datos
        db.collection("Rutas").whereEqualTo("ID RUTAS",id_ruta)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {



                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //Log.d("___________ENTRO", "______________****_________");

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("___________ENTRO", document.getId() + " => " + document.getData());
                                //Toast.makeText(MainActivity22.this, "ENTRO CON ID:" +id_ruta, Toast.LENGTH_SHORT).show();

                                Glide.with(getApplication())
                                        .load(document.getData().get("backgroundUrl"))
                                        .fitCenter()
                                        .centerCrop()
                                        .into(imageView_principal);//.centerCrop()

                                textView_TITLE_PRINCIPAL.setText(document.getData().get("title").toString());
                                textView_UBICACION_PRINCIPAL.setText(document.getData().get("ubicacion").toString());
                                textView_VALORACION_PRINCIPAL.setText(document.getData().get("valoracion").toString());


                            }
                        } else {
                            Log.w("___________TAG", "Error getting documents.", task.getException());
                            Toast.makeText(MainActivity22.this, "NOOOO ENTRO CON ID:" +id_ruta, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //////////////// end base de datos
    }

    public void btn_albergues(View v)
    {
        Toast.makeText(this, "id:" + id_ruta, Toast.LENGTH_SHORT).show();
    }
    public void btn_recomendaciones(View v)
    {
        Toast.makeText(this, "id:" + id_ruta, Toast.LENGTH_SHORT).show();
    }
    public void btn_pueblos(View v)
    {
        Toast.makeText(this, "id:" + id_ruta, Toast.LENGTH_SHORT).show();
    }
    public void btn_servicios(View v)
    {
        Toast.makeText(this, "id:" + id_ruta, Toast.LENGTH_SHORT).show();
    }


}
