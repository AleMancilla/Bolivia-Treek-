package com.example.boliviatreek;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    public List<Product> userList;
    public Context context;
    public int ii=0;

    public Adaptador ( Context x ,List<Product> userList)
    {
        this.userList=userList;
        this.context=x;
       // Log.d("ENTRO A CLASE_____", "_______________________________________"+userList.get(ii).tostring());
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //return null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rutas,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ii=i;

        Log.d("PRUEBA____", "GETI userList "+userList.get(i));
        viewHolder.title.setText(userList.get(i).getTitle());
        viewHolder.ubicacion.setText(userList.get(i).getUbicacion());
        viewHolder.kilometraje.setText(userList.get(i).getKilometraje());
        viewHolder.distancia.setText(userList.get(i).getDistancia());
        viewHolder.modalidad.setText(userList.get(i).getModalidad());
        viewHolder.dificultad.setText(userList.get(i).getDificultad());
        viewHolder.valoracion.setText(userList.get(i).getValoracion());
        viewHolder.NickNameUser.setText(userList.get(i).getNicknameuser());



        Glide.with(context)
                .load(userList.get(i).getIconUrl())
                .fitCenter()
                .into(viewHolder.imagen_logo);//.centerCrop()
        Glide.with(context)
                .load(userList.get(i).getBackgroundUrl())
                .fitCenter()
                .centerCrop()
                .into(viewHolder.imagen_fondo);//.centerCrop()


        final String user_id = userList.get(i).userID;
        viewHolder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "ID: "+user_id, Toast.LENGTH_SHORT).show();

                enterView(user_id);
            }
        });

        //return convertView;

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        View convertView;
        public TextView title;
        public TextView ubicacion ;
        public TextView kilometraje ;
        public TextView distancia ;
        public TextView modalidad ;
        public TextView dificultad ;
        public TextView valoracion ;
        public TextView NickNameUser;

        public ImageView imagen_logo ;//= convertView.findViewById(R.id.imgv_logo);
        public ImageView imagen_fondo ;//= convertView.findViewById(R.id.imgv_fondo);


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            convertView = itemView;


            title = convertView.findViewById(R.id.tv_title);
            ubicacion = convertView.findViewById(R.id.tv_ubicacion);
            kilometraje = convertView.findViewById(R.id.tv_kilometraje);
            distancia = convertView.findViewById(R.id.tv_distancia);
            modalidad = convertView.findViewById(R.id.tv_modalidad);
            dificultad = convertView.findViewById(R.id.tv_dificultad);
            valoracion = convertView.findViewById(R.id.tv_valoracion);
            NickNameUser = convertView.findViewById(R.id.tv_creadUser);

            imagen_logo = convertView.findViewById(R.id.imgv_logo);
            imagen_fondo = convertView.findViewById(R.id.imgv_fondo);

        }
    }
    public void enterView(String v)
    {
        Intent intent = new Intent(context, MainActivity22.class);
        context.startActivity(intent);
    }


    public String tostring()
    {
        return userList.get(ii).getTitle();
    }


}







    /*

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
*/
