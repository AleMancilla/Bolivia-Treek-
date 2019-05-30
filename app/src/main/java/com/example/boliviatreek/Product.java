package com.example.boliviatreek;

import java.io.Serializable;

public class Product implements Serializable {

    public String title = "";
    public String ubicacion= "";
    public String kilometraje= "";
    public String distancia= "";
    public String modalidad= "";
    public String dificultad= "";
    public String valoracion= "";
    public String backgroundUrl = "";
    public String iconUrl = "";
    public String nicknameuser = "";

    public Product() {
    }

    public Product(String title, String ubicacion, String kilometraje, String distancia, String modalidad, String dificultad, String valoracion, String backgroundUrl, String iconUrl, String nicknameuser) {
        this.title = title;
        this.ubicacion = ubicacion;
        this.kilometraje = kilometraje;
        this.distancia = distancia;
        this.modalidad = modalidad;
        this.dificultad = dificultad;
        this.valoracion = valoracion;
        this.backgroundUrl = backgroundUrl;
        this.iconUrl = iconUrl;
        this.nicknameuser = nicknameuser;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String tostring()
    {
        return title + " , "+ubicacion + " , "+kilometraje + " , "+distancia + " , "+modalidad + " , "+dificultad + " , "+valoracion ;
    }

    public String getTitle() {
        if(title!=null)
        {return title;}
        return "";
    }

    public String getNicknameuser() {
        return nicknameuser;
    }

    public void setNicknameuser(String nicknameuser) {
        this.nicknameuser = nicknameuser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
}