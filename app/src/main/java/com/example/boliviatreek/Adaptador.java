package com.example.boliviatreek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {

    @Override
    public int getCount() {
        return datos_img.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /////////////////////
    private static LayoutInflater inflater = null;

    Context contexto;
    String [][] datos_txt;
    int[] datos_img;

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ////////// codigo para acomodar elementos de la list view
        final View vista = inflater.inflate(R.layout.list_item_rutas, null);



        TextView title = (TextView) vista.findViewById(R.id.tv_title);
        TextView ubicacion = (TextView) vista.findViewById(R.id.tv_ubicacion);
        TextView kilometraje = (TextView) vista.findViewById(R.id.tv_kilometraje);
        TextView distancia = (TextView) vista.findViewById(R.id.tv_distancia);
        TextView modalidad = (TextView) vista.findViewById(R.id.tv_modalidad);
        TextView dificultad = (TextView) vista.findViewById(R.id.tv_dificultad);
        TextView valoracion = (TextView) vista.findViewById(R.id.tv_valoracion);

        ImageView imagen_logo = (ImageView) vista.findViewById(R.id.imgv_logo);
        ImageView imagen_fondo = (ImageView) vista.findViewById(R.id.imgv_fondo);

        title.setText(datos_txt[i][0]);
        ubicacion.setText(datos_txt[i][1]);
        kilometraje.setText(datos_txt[i][2]);
        distancia.setText(datos_txt[i][3]);
        modalidad.setText(datos_txt[i][4]);
        dificultad.setText(datos_txt[i][5]);
        valoracion.setText(datos_txt[i][6]);

        imagen_logo.setImageResource(datos_img[i]);
        imagen_fondo.setImageResource(R.mipmap.icontrek);

/*
        imagen.setTag(i);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visorImagen = new Intent(contexto, VisorImagen.class);
                visorImagen.putExtra("IMG", datosImg[(Integer)v.getTag()]);
                contexto.startActivity(visorImagen);
            }
        });

*/
        return vista;
        //// fin de codigo para acomodar la list view
    }


    public Adaptador (Context contexto, String[][] datos_txt, int[]datos_img)
    {
        this.contexto = contexto;
        this.datos_txt=datos_txt;
        this.datos_img=datos_img;

        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }
}
