package com.radenmas.system.monitoring.hydroponic.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.radenmas.system.R;

public class HistoryFragment extends Fragment {
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;
    TextView nameChart;
    ImageButton btnBackRound, btnNextRound;

    int count = 0;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        initView(view);
        nameChart = view.findViewById(R.id.name_chart);
        nameChart.setText("Grafik Temperature");

        getFragmentManager().beginTransaction().replace(R.id.content_chart, new ChartTemp()).commit();

        btnBackRound.setOnClickListener(view1 -> {
            if (count <= 0) {
                btnBackRound.setClickable(false);
                btnNextRound.setClickable(true);
            } else {
                btnBackRound.setClickable(true);
                count = count - 1;
                changeChart(count);
            }
        });

        btnNextRound.setOnClickListener(view1 -> {
            if (count >= 4) {
                btnBackRound.setClickable(true);
                btnNextRound.setClickable(false);
            } else {
                btnNextRound.setClickable(true);
                count = count + 1;
                changeChart(count);
            }
        });


//        count = view.findViewById(R.id.count);

        // layout xml slide 1 sampai 5
//        layouts = new int[]{
//                R.layout.fragment_chart_temp,
//                R.layout.fragment_chart_hum,
//                R.layout.fragment_chart_ph,
//                R.layout.fragment_chart_tds,
//                R.layout.fragment_chart_water};

        // tombol dots (lingkaran kecil perpindahan slide)
//        addBottomDots(0);

//        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
//        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        return view;
    }

    private void changeChart(int count) {
        Fragment selectedFragment = null;
        switch (count) {
            case 0:
                selectedFragment = new ChartTemp();
                nameChart.setText("Grafik Temperature");
                break;
            case 1:
                selectedFragment = new ChartHum();
                nameChart.setText("Grafik Humidity");
                break;
            case 2:
                selectedFragment = new ChartPH();
                nameChart.setText("Grafik PH");
                break;
            case 3:
                selectedFragment = new ChartTDS();
                nameChart.setText("Grafik TDS");
                break;
            case 4:
                selectedFragment = new ChartWater();
                nameChart.setText("Grafik Water");
                break;
        }
        getFragmentManager().beginTransaction().replace(R.id.content_chart, selectedFragment).commit();
        return;
    }

    //    private void addBottomDots(int currentPage) {
//        TextView[] dots = new TextView[5];
//
//        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
//        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
//
//        dotsLayout.removeAllViews();
////        for (int i = 0; i < dots.length; i++) {
//        for (int i = 0; i < 5; i++) {
//            dots[i] = new TextView(getContext());
//            dots[i].setText(Html.fromHtml("&#8226;"));
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(colorsInactive[currentPage]);
//            dotsLayout.addView(dots[i]);
//        }
//
//        if (dots.length > 0)
//            dots[currentPage].setTextColor(colorsActive[currentPage]);
//    }
//
    //  viewpager change listener
//    final ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
//
//        @Override
//        public void onPageSelected(int position) {
////            addBottomDots(position);
//            count.setText(""+position);
//        }
//
//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//            Toast.makeText(getContext(), ""+arg0, Toast.LENGTH_SHORT).show();
//        }
//    };

    private void initView(View view) {
//        viewPager = view.findViewById(R.id.vp_chart);
        dotsLayout = view.findViewById(R.id.layout_dots);
        btnBackRound = view.findViewById(R.id.btn_back_round);
        btnNextRound = view.findViewById(R.id.btn_next_round);
    }
}