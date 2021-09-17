package com.radenmas.system.monitoring.hydroponic.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.radenmas.system.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

@SuppressLint("ViewConstructor")
public class MyMarkerView extends MarkerView {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm zz", Locale.getDefault());
    private final TextView tvValue, tvTime;

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvValue = findViewById(R.id.tvValue);
        tvTime = findViewById(R.id.tvTime);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvValue.setText(Utils.formatNumber(ce.getHigh(), 0, true));
        } else {
            float time = e.getX();
            String jam = sdf.format(time);
            tvTime.setText(jam);
            tvValue.setText(Utils.formatNumber(e.getY(), 0, true));
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}