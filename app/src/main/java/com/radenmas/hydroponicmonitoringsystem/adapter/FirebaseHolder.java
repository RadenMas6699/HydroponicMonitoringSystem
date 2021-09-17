package com.radenmas.hydroponicmonitoringsystem.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radenmas.hydroponicmonitoringsystem.R;

public class FirebaseHolder extends RecyclerView.ViewHolder {

    public final TextView itemDateTime, itemTemperature, itemHumidity, itemTds, itemWater, itemPh;

    public FirebaseHolder(@NonNull View itemView) {
        super(itemView);
        itemDateTime = itemView.findViewById(R.id.item_date_time);
        itemTemperature = itemView.findViewById(R.id.item_temperature);
        itemHumidity = itemView.findViewById(R.id.item_humidity);
        itemTds = itemView.findViewById(R.id.item_tds);
        itemWater = itemView.findViewById(R.id.item_water);
        itemPh = itemView.findViewById(R.id.item_ph);
    }
}
