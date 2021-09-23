package com.radenmas.system.monitoring.hydroponic.adapter;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.radenmas.system.R;

public class SelectTextView {

    @SuppressLint("ResourceAsColor")
    public void select(TextView tvSelected, TextView tvUnselected1, TextView tvUnselected2) {
        tvSelected.setBackgroundResource(R.drawable.bg_btn_selected);
        tvUnselected1.setBackgroundResource(R.drawable.bg_btn_unselected);
        tvUnselected2.setBackgroundResource(R.drawable.bg_btn_unselected);

        tvSelected.setTextColor(R.color.green_strong);
        tvUnselected1.setTextColor(android.R.color.darker_gray);
        tvUnselected2.setTextColor(android.R.color.darker_gray);
    }

}
