package com.radenmas.system.monitoring.hydroponic.ui.fragment;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.radenmas.system.R;
import com.radenmas.system.monitoring.hydroponic.adapter.DataPoints;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private DatabaseReference dbReff;
    private TextView valueDate, valueClock,
            valueTemperature, statusTemperature,
            valueHumidity, statusHumidity,
            valueTDS, statusTDS,
            valueWater, statusWater,
            valuePH, statusPH;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        getData();

        return view;
    }

    private void getData() {
        dbReff = FirebaseDatabase.getInstance().getReference("hydroponic");
        Query lastUpdate = dbReff.orderByKey().limitToLast(1);
        lastUpdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataPoints data = dataSnapshot.getValue(DataPoints.class);

                    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                    cal.setTimeInMillis(data.getTime() * 1000);

                    String clock = DateFormat.format("HH:mm zz", cal).toString();
                    String date = DateFormat.format("dd MMM yyyy", cal).toString();

                    valueClock.setText(clock);
                    valueDate.setText(date);

                    DecimalFormat koma = new DecimalFormat("#.##");

                    valueTemperature.setText("" + data.getTempRuang() + "\u2103");
                    valueHumidity.setText("" + data.getHumRuang() + "%");
                    valueTDS.setText("" + data.getTdsAir());
                    valueWater.setText("" + data.getTempAir() + "\u2103");
                    valuePH.setText(koma.format(data.getPhAir()));

                    float temp, hum, ph, tds, wtr;
                    temp = data.getTempRuang();
                    hum = data.getHumRuang();
                    ph = data.getPhAir();
                    tds = data.getTdsAir();
                    wtr = data.getTempAir();

                    //sedang //baik //sedang //buruk
                    setStatus(temp, 0, 15, 35, 50, statusTemperature);
                    setStatus(hum, 0, 25, 75, 100, statusHumidity);
                    setStatus(ph, 6, 6.5, 7.5, 8, statusPH);
                    setStatus(tds, 0, 500, 2500, 3000, statusTDS);
                    setStatus(wtr, 0, 15, 35, 50, statusWater);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void setStatus(float value, double i, double j, double k, double l, TextView status) {
        if (value > i && value <= j) {
            //medium
            status.setBackground(getResources().getDrawable(R.drawable.bg_medium_status));
            status.setText(R.string.medium);
        } else if (value > j && value <= k) {
            //baik
            status.setBackground(getResources().getDrawable(R.drawable.bg_good_status));
            status.setText(R.string.good);
        } else if (value > k && value <= l) {
            //medium
            status.setBackground(getResources().getDrawable(R.drawable.bg_medium_status));
            status.setText(R.string.medium);
        } else {
            //buruk
            status.setBackground(getResources().getDrawable(R.drawable.bg_bad_status));
            status.setText(R.string.bad);
        }
    }

    private void initView(View view) {
        valueDate = view.findViewById(R.id.value_date);
        valueClock = view.findViewById(R.id.value_clock);

        valueTemperature = view.findViewById(R.id.value_temperature);
        statusTemperature = view.findViewById(R.id.status_temperature);

        valueHumidity = view.findViewById(R.id.value_humidity);
        statusHumidity = view.findViewById(R.id.status_humidity);

        valueTDS = view.findViewById(R.id.value_tds);
        statusTDS = view.findViewById(R.id.status_tds);

        valueWater = view.findViewById(R.id.value_water);
        statusWater = view.findViewById(R.id.status_water);

        valuePH = view.findViewById(R.id.value_ph);
        statusPH = view.findViewById(R.id.status_ph);
    }


}