package com.radenmas.system.monitoring.hydroponic.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.radenmas.system.monitoring.hydroponic.ui.fragment.ChartHum;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.ChartPH;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.ChartTDS;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.ChartTemp;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.ChartWater;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ChartTemp();
            case 1:
                return new ChartHum();
            case 2:
                return new ChartPH();
            case 3:
                return new ChartTDS();
            case 4:
                return new ChartWater();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
