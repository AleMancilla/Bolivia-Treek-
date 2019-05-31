package com.example.boliviatreek;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Product> {

    Context context;
    ArrayList<Product> object;




    @Override
    public Product getItem(int position) {
        return object.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /////////////////////
    //private static LayoutInflater inflater = null;

    //Context contexto;
    //int[] datos_img;

    //Adaptador activity= this;

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ////////// codigo para acomodar elementos de la list view
        //Log.d("*****prueba****", "entraaa 1"+i  );
        if(convertView == null){
            //Log.d("*****prueba****", "entraaa 2" );
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.list_item_rutas,parent,false);
        }

            //final View vista = inflater.inflate(R.layout.list_item_rutas, null);
            Product dir = getItem(i);


            TextView title = convertView.findViewById(R.id.tv_title);
            TextView ubicacion = convertView.findViewById(R.id.tv_ubicacion);
            TextView kilometraje = convertView.findViewById(R.id.tv_kilometraje);
            TextView distancia = convertView.findViewById(R.id.tv_distancia);
            TextView modalidad = convertView.findViewById(R.id.tv_modalidad);
            TextView dificultad = convertView.findViewById(R.id.tv_dificultad);
            TextView valoracion = convertView.findViewById(R.id.tv_valoracion);
            TextView NickNameUser = convertView.findViewById(R.id.tv_creadUser);

            ImageView imagen_logo = convertView.findViewById(R.id.imgv_logo);
            ImageView imagen_fondo = convertView.findViewById(R.id.imgv_fondo);


            title.setText(dir.getTitle());
            ubicacion.setText(dir.getUbicacion());
            kilometraje.setText(dir.getKilometraje());
            distancia.setText(dir.getDistancia());
            modalidad.setText(dir.getModalidad());
            dificultad.setText(dir.getDificultad());
            valoracion.setText(dir.getValoracion());
            NickNameUser.setText(dir.getNicknameuser());


                Glide.with(context)
                .load(dir.getIconUrl())
                .fitCenter()
                .into(imagen_logo);//.centerCrop()
        Glide.with(context)
                .load(dir.getBackgroundUrl())
                .fitCenter()
                .centerCrop()
                .into(imagen_fondo);//.centerCrop()

            return convertView;
            //// fin de codigo para acomodar la list view

    }


    public Adaptador(Context context, ArrayList<Product> object){
        super(context,0, object);
        this.object = object;
        this.context=context;
    }
}
