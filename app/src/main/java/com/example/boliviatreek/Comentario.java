package com.example.boliviatreek;

import android.content.Context;
import android.text.format.Time;

import java.util.List;

public class Comentario extends ComentarioID{

    private String url_perfil;
    private String nickname;
    private String comentario;

    private String  fecha_registro;

    public Comentario()
    {

    }

    public Comentario(String url_perfil, String nickname, String comentario, String fecha_registro) {
        this.url_perfil = url_perfil;
        this.nickname = nickname;
        this.comentario = comentario;
        this.fecha_registro=fecha_registro;
    }


    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getUrl_perfil() {
        return url_perfil;
    }

    public void setUrl_perfil(String url_perfil) {
        this.url_perfil = url_perfil;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


//    fecha_registro = new Time(Time.getCurrentTimezone());
//        fecha_registro.setToNow();
//    int idia = fecha_registro.monthDay;
//    int imes= fecha_registro.month+1;
//    int iyear = fecha_registro.year;
//    final String fecha_registro_sistema = idia + "/" + imes + "/" + iyear;



}
