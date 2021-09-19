package com.radenmas.system.monitoring.hydroponic.ui.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.radenmas.system.R;
import com.radenmas.system.monitoring.hydroponic.adapter.MyPagerAdapter;

public class HistoryFragment extends Fragment {
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        initView(view);

        // layout xml slide 1 sampai 5
//        layouts = new int[]{
//                R.layout.fragment_chart_temp,
//                R.layout.fragment_chart_hum,
//                R.layout.fragment_chart_ph,
//                R.layout.fragment_chart_tds,
//                R.layout.fragment_chart_water};

        // tombol dots (lingkaran kecil perpindahan slide)
        addBottomDots(0);

        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        return view;
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[5];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
//        for (int i = 0; i < dots.length; i++) {
        for (int i = 0; i < 5; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    //  viewpager change listener
    final ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    private void initView(View view) {
        viewPager = view.findViewById(R.id.vp_chart);
        dotsLayout = view.findViewById(R.id.layout_dots);
    }
}