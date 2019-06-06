package com.example.boliviatreek.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.boliviatreek.R;

//import com.example.viewpagerdesde0.R;

@SuppressLint("ValidFragment")
public class pageFragment1 extends Fragment {

    private TextView textView;

    private String id_ruta;

    //@SuppressLint("ValidFragment")
    public pageFragment1(String id_ruta) {
        this.id_ruta=id_ruta;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.view_pager_1,container
                        ,false);

         textView = rootView.findViewById(R.id.textView_Title_VP);
         textView.setText(id_ruta);

        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
        //
    }

}
