package com.radenmas.system.monitoring.hydroponic.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.radenmas.system.R;

public class HistoryFragment extends Fragment {
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

    private void initView(View view) {
        btnBackRound = view.findViewById(R.id.btn_back_round);
        btnNextRound = view.findViewById(R.id.btn_next_round);
    }
}