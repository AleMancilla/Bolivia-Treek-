package com.example.boliviatreek;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterDatosComentarios extends RecyclerView.Adapter<AdapterDatosComentarios.ViewHolderComentarios> {

    public List<Comentario> listComentario;
    public Context context;
    public int ii;

    public AdapterDatosComentarios( Context context,List<Comentario> listComentario) {
        this.listComentario = listComentario;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolderComentarios onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_comentarios,null,false);

        return new ViewHolderComentarios(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderComentarios viewHolderComentarios, int i) {
        viewHolderComentarios.nickname.setText(listComentario.get(i).getNickname());
       viewHolderComentarios.fecha_comentario.setText(listComentario.get(i).getFecha_registro());
        viewHolderComentarios.comentario.setText(listComentario.get(i).getComentario());
//        viewHolderComentarios.url_perfil_photo.setImageResource(R.drawable.googleg_disabled_color_18);

        Glide.with(context)
                .load(listComentario.get(i).getUrl_perfil())
                .fitCenter()
                .into(viewHolderComentarios.url_perfil_photo);//.centerCrop()
        Log.d("_________________","url imagen ____________"+listComentario.get(i).getUrl_perfil());
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
