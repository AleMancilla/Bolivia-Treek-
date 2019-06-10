package com.example.boliviatreek;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterDatosComentarios extends RecyclerView.Adapter<AdapterDatosComentarios.ViewHolderComentarios> {

    ArrayList<Comentario> listComentario;

    public AdapterDatosComentarios(ArrayList<Comentario> listComentario) {
        this.listComentario = listComentario;
    }

    @NonNull
    @Override
    public ViewHolderComentarios onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_comentarios,null,false);

        return new ViewHolderComentarios(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderComentarios viewHolderComentarios, int i) {
        viewHolderComentarios.nickname.setText("nickname");
       viewHolderComentarios.fecha_comentario.setText("fecha");
        viewHolderComentarios.comentario.setText("Comentario");
        viewHolderComentarios.url_perfil_photo.setImageResource(R.drawable.googleg_disabled_color_18);

    }

    @Override
    public int getItemCount() {
        return listComentario.size();
    }

    public class ViewHolderComentarios extends RecyclerView.ViewHolder {

        TextView nickname;
        TextView fecha_comentario;
        TextView comentario;
        ImageView url_perfil_photo;



        public ViewHolderComentarios(@NonNull View itemView) {
            super(itemView);

             nickname = itemView.findViewById(R.id.textView_nickname_comentarios);
             fecha_comentario= itemView.findViewById(R.id.textView_fecha_comentario);
             comentario= itemView.findViewById(R.id.textView_comentario);
             url_perfil_photo= itemView.findViewById(R.id.imageView_photo_perfil);
        }
    }
}
