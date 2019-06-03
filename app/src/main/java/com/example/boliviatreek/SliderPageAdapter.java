package com.example.boliviatreek;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class SliderPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public SliderPageAdapter(FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        this.fragmentList=fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle (int position)
    {
        switch (position)
        {
            case 0:
                return "INFORMACION";
            //break;
            case 1:
                return  "MAPA";
            case 2:
                return "FOTOS";

        }
        return null;
    }
}
