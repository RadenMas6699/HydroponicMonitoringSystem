package com.radenmas.system.monitoring.hydroponic.ui.fragment;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.radenmas.system.monitoring.hydroponic.R;
import com.radenmas.system.monitoring.hydroponic.adapter.DataPoints;
import com.radenmas.system.monitoring.hydroponic.adapter.MyMarkerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ChartWater extends Fragment {
    LineChart chart;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;

    private int statusSortir = 0;
    private TextView tvDay, tvWeek, tvMonth;

    // day 1440
    // week 10080
    // month 43200

    private int day = 1440;
    private int week = 10080;
    private int month = 43200;

    public ChartWater() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        initView(view);
        onClick();

        selectTextView(tvDay, tvWeek, tvMonth);

        switch (statusSortir) {
            case 0:
                Graph(day);
                break;
            case 1:
                Graph(week);
                break;
            case 2:
                Graph(month);
                break;
        }

        chart.getDescription().setEnabled(false);
        chart.setNoDataText(getString(R.string.water));
        chart.setNoDataTextColor(getResources().getColor(R.color.dark_icon));
        chart.invalidate();

        return view;
    }

    private void onClick() {
        tvDay.setOnClickListener(view -> {
            statusSortir = 0;
            selectTextView(tvDay, tvWeek, tvMonth);
            switch (statusSortir) {
                case 0:
                    Graph(day);
                    break;
                case 1:
                    Graph(week);
                    break;
                case 2:
                    Graph(month);
                    break;
            }
            chart.invalidate();
        });
        tvWeek.setOnClickListener(view -> {
            statusSortir = 1;
            selectTextView(tvWeek, tvDay, tvMonth);
            switch (statusSortir) {
                case 0:
                    Graph(day);
                    break;
                case 1:
                    Graph(week);
                    break;
                case 2:
                    Graph(month);
                    break;
            }
            chart.invalidate();
        });
        tvMonth.setOnClickListener(view -> {
            statusSortir = 2;
            selectTextView(tvMonth, tvWeek, tvDay);
            switch (statusSortir) {
                case 0:
                    Graph(day);
                    break;
                case 1:
                    Graph(week);
                    break;
                case 2:
                    Graph(month);
                    break;
            }
            chart.invalidate();
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
                        data.add(new Entry(dataPoints.getTime(), dataPoints.getTempAir()));
                    }
                    showChart(data);
                    lineDataSet.setDrawCircles(false);
                }
                chart.invalidate();
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
        lineDataSet.setLineWidth(1.5f);
        lineDataSet.setDrawValues(false);
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);

        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setCenterAxisLabels(true);
        xAxis.setTextSize(10f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawLabels(true);

        switch (statusSortir) {
            case 0: // hari
                xAxis.setLabelRotationAngle(0f);//45
                xAxis.setLabelCount(7, true);
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        long longtime = (long) value;
                        Calendar cal = Calendar.getInstance(Locale.getDefault());
                        cal.setTimeInMillis(longtime * 1000);
                        String s = DateFormat.format("HH:mm", cal).toString();
                        return s;
                    }
                });
                chart.invalidate();
                break;
            case 1: // minggu
                xAxis.setLabelRotationAngle(0f);//45
                xAxis.setLabelCount(8, true);
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        long longtime = (long) value;
                        Calendar cal = Calendar.getInstance(Locale.getDefault());
                        cal.setTimeInMillis(longtime * 1000);
                        String s = DateFormat.format("dd MMM", cal).toString();
                        return s;
                    }
                });
                chart.invalidate();
                break;
            case 2: // bulan
                xAxis.setLabelRotationAngle(-45f);//45
                xAxis.setLabelCount(17, true);
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        long longtime = (long) value;
                        Calendar cal = Calendar.getInstance(Locale.getDefault());
                        cal.setTimeInMillis(longtime * 1000);
                        String s = DateFormat.format("dd MMM", cal).toString();
                        return s;
                    }
                });
                chart.invalidate();
                break;
        }

        YAxis yAxisL = chart.getAxis(YAxis.AxisDependency.LEFT);
        yAxisL.setDrawGridLines(false);
        yAxisL.setDrawLabels(true);
        yAxisL.setAxisMinimum(0);
        yAxisL.setAxisMaximum(40);

//        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
//        mv.setChartView(chart);
//        chart.setMarker(mv);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
//        chart.getAxisLeft().setEnabled(false);
        chart.notifyDataSetChanged();
        chart.clear();
        chart.setData(lineData);
        chart.invalidate();
        chart.moveViewTo(lineData.getEntryCount(), 50L, YAxis.AxisDependency.LEFT);
    }

    private void selectTextView(TextView tvSelected, TextView tvUnselected1, TextView tvUnselected2) {
        tvSelected.setBackgroundResource(R.drawable.bg_btn_selected);
        tvUnselected1.setBackgroundResource(R.drawable.bg_btn_unselected);
        tvUnselected2.setBackgroundResource(R.drawable.bg_btn_unselected);

        tvSelected.setTextColor(getResources().getColor(R.color.green_strong));
        tvUnselected1.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tvUnselected2.setTextColor(getResources().getColor(android.R.color.darker_gray));
    }
}