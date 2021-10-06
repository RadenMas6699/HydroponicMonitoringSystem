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
import com.radenmas.system.monitoring.hydroponic.R;
import com.radenmas.system.monitoring.hydroponic.adapter.DataPoints;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private DatabaseReference dbReff, dbTime;
    private TextView valueDate, valueClock, valueTemperature, valueHumidity, valueTDS, valueWater, valuePH;

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
        //getTime
        dbTime = FirebaseDatabase.getInstance().getReference("timestamp");
        dbTime.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String times = snapshot.getValue().toString();
                Long timesL = Long.parseLong(times);
                Calendar cal = Calendar.getInstance(Locale.getDefault());
                cal.setTimeInMillis(timesL);

                String clock = DateFormat.format("HH:mm zz", cal).toString();
                String date = DateFormat.format("dd MMM yyyy", cal).toString();

                valueClock.setText(clock);
                valueDate.setText(date);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        dbReff = FirebaseDatabase.getInstance().getReference("hydroponic");
        Query lastUpdate = dbReff.orderByKey().limitToLast(1);
        lastUpdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataPoints data = dataSnapshot.getValue(DataPoints.class);
                    DecimalFormat koma = new DecimalFormat("#.##");

                    valueTemperature.setText("" + data.getTempRuang() + "\u2103");
                    valueHumidity.setText("" + data.getHumRuang() + "%");
                    valueTDS.setText("" + data.getTdsAir());
                    valueWater.setText("" + data.getTempAir() + "\u2103");
                    valuePH.setText(koma.format(data.getPhAir()));
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void initView(View view) {
        valueDate = view.findViewById(R.id.value_date);
        valueClock = view.findViewById(R.id.value_clock);
        valueTemperature = view.findViewById(R.id.value_temperature);
        valueHumidity = view.findViewById(R.id.value_humidity);
        valueTDS = view.findViewById(R.id.value_tds);
        valueWater = view.findViewById(R.id.value_water);
        valuePH = view.findViewById(R.id.value_ph);
    }


}