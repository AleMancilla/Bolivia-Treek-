package com.example.boliviatreek.fragments;

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
import com.example.boliviatreek.extra1;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

//import com.example.viewpagerdesde0.R;

public class pageFragment1 extends Fragment  {

    private TextView t=null;
    String myid;
    private EventBus bus = EventBus.getDefault();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            myid = bundle.getString("id","PRUEBA ");
//        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



         ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.view_pager_1,container
                        ,false);

         t=rootView.findViewById(R.id.textView_title_info);

        bus.register(this);

//
//        t.setText(myid+" holo ");


        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
        //
    }

    @Subscribe
    public void ejecutarLlamada(extra1 ext)
    {

        t.setText(myid+" holo "+ext.id);
    }

}
