package com.example.boliviatreek;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.boliviatreek.fragments.pageFragment1;
import com.example.boliviatreek.fragments.pageFragment2;
import com.example.boliviatreek.fragments.pageFragment3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity22 extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        List<Fragment> list = new ArrayList<>();
        list.add(new pageFragment1());
        list.add(new pageFragment2());
        list.add(new pageFragment3());

        pager = findViewById(R.id.id_ViewPager);
        pagerAdapter = new SliderPageAdapter(getSupportFragmentManager(),list);

        pager.setAdapter(pagerAdapter);

    }


}
