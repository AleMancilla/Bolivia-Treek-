package com.example.boliviatreek.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boliviatreek.R;

@SuppressLint("ValidFragment")
public class pageFragment2 extends Fragment {

    private String id_ruta;


    public pageFragment2(String id_ruta) {
        this.id_ruta=id_ruta;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.view_page2,container
                        ,false);

        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
        //
    }
}
