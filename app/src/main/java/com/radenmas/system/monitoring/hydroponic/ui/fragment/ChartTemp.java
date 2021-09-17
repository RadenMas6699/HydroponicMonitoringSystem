package com.radenmas.system.monitoring.hydroponic.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.radenmas.system.R;
import com.radenmas.system.monitoring.hydroponic.adapter.DataPoints;
import com.radenmas.system.monitoring.hydroponic.adapter.MyMarkerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ChartTemp extends Fragment {
    LineChart chart;
    LineDataSet lineDataSet = new LineDataSet(null,null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;

    private int statusSortir = 0;
    private TextView tvDay, tvWeek, tvMonth;

    public ChartTemp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart_temp, container, false);

        initView(view);
        onClick();

        selectTextView(tvDay, tvWeek, tvMonth);

        Graph(144);
        chart.getDescription().setEnabled(false);
        chart.setNoDataText(getString(R.string.app_name));
        chart.setNoDataTextColor(getResources().getColor(R.color.dark_icon));
        chart.invalidate();

        return view;
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
        chart = view.findViewById(R.id.chartTemp);
    }

    public void Graph(final int limit) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("hydroponic");
        Query query = databaseReference.orderByKey().limitToLast(limit);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> data = new ArrayList<>();
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {

                        DataPoints dataPoints = child.getValue(DataPoints.class);
                        data.add(new Entry(dataPoints.getTime(), dataPoints.getTempRuang()));
                    }
                    showChart(data);
                    lineDataSet.setDrawCircles(false);
                    chart.invalidate();
                } else {
                    chart.clear();
                    chart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showChart(ArrayList<Entry> data) {
        lineDataSet.setValues(data);
        lineDataSet.setLabel("DataSet 1");
        lineDataSet.setDrawFilled(true);
        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_blue_light);
            lineDataSet.setFillDrawable(drawable);
        } else {
            lineDataSet.setFillAlpha(5);
        }
        lineDataSet.setLineWidth(1.5f);
        lineDataSet.setDrawValues(false);
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);

        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(0f);//45
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawLabels(false);
        xAxis.setLabelCount(3, true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Date date = new Date((long) value);
                SimpleDateFormat fmt;
                fmt = new SimpleDateFormat("HH:mm zz");
                fmt.setTimeZone(TimeZone.getDefault());
                String s = fmt.format(date);
                return s;
            }
        });

        YAxis yAxisL = chart.getAxis(YAxis.AxisDependency.LEFT);
        yAxisL.setDrawGridLines(false);
        yAxisL.setDrawLabels(false);
        yAxisL.setAxisMinimum(15);
        yAxisL.setAxisMaximum(45);

        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
        mv.setChartView(chart);
        chart.setMarker(mv);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
//        chart.getAxisRight().setEnabled(false);
//        chart.getAxisLeft().setEnabled(false);
//        chart.getXAxis().setEnabled(false);
        chart.notifyDataSetChanged();
        chart.clear();
        chart.setData(lineData);
        chart.invalidate();
        chart.moveViewTo(lineData.getEntryCount(),50L, YAxis.AxisDependency.LEFT);
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