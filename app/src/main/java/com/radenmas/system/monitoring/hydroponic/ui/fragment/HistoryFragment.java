package com.radenmas.system.monitoring.hydroponic.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.radenmas.system.R;

public class HistoryFragment extends Fragment {
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private int statusSortir = 0;
    private TextView tvDay, tvWeek, tvMonth;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        initView(view);
        onClick();

        selectTextView(tvDay, tvWeek, tvMonth);

        // layout xml slide 1 sampai 5
        layouts = new int[]{
                R.layout.fragment_chart_temp,
                R.layout.fragment_chart_hum,
                R.layout.fragment_chart_ph,
                R.layout.fragment_chart_tds,
                R.layout.fragment_chart_water};

        // tombol dots (lingkaran kecil perpindahan slide)
        addBottomDots(0);

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        return view;
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
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
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            addBottomDots(position);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {

        public MyViewPagerAdapter() {
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void onClick() {
        tvDay.setOnClickListener(view -> {
            statusSortir = 0;
            selectTextView(tvDay, tvWeek, tvMonth);
        });
        tvWeek.setOnClickListener(view -> {
            statusSortir = 1;
            selectTextView(tvWeek, tvDay, tvMonth);
        });
        tvMonth.setOnClickListener(view -> {
            statusSortir = 2;
            selectTextView(tvMonth, tvWeek, tvDay);
        });
    }

    private void initView(View view) {
        tvDay = view.findViewById(R.id.tv_day);
        tvWeek = view.findViewById(R.id.tv_week);
        tvMonth = view.findViewById(R.id.tv_month);

        viewPager = view.findViewById(R.id.vp_chart);
        dotsLayout = view.findViewById(R.id.layout_dots);
    }

    private void selectTextView(TextView tvSelected, TextView tvUnselected1, TextView tvUnselected2) {
        tvSelected.setBackground(getResources().getDrawable(R.drawable.bg_btn_selected));
        tvUnselected1.setBackground(getResources().getDrawable(R.drawable.bg_btn_unselected));
        tvUnselected2.setBackground(getResources().getDrawable(R.drawable.bg_btn_unselected));

        tvSelected.setTextColor(getResources().getColor(R.color.green_strong));
        tvUnselected1.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tvUnselected2.setTextColor(getResources().getColor(android.R.color.darker_gray));
    }
}