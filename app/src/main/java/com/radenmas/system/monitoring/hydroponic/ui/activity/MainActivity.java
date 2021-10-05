package com.radenmas.system.monitoring.hydroponic.ui.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.radenmas.system.monitoring.hydroponic.R;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.HistoryFragment;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.HomeFragment;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.InfoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView titleActivity = findViewById(R.id.titleActivity);

        titleActivity.setText("Monitoring Status");
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new HomeFragment()).commit();

        if (checkInternet()) {
            Toast.makeText(this, "Connect", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Disconnect", Toast.LENGTH_SHORT).show();
        }

        BottomNavigationView navigationView = findViewById(R.id.nav_bottom);
//        navigationView.setOnNavigationItemSelectedListener(item -> {
//            Fragment selectedFragment = null;
//            String title = null;
//
//            switch (item.getItemId()) {
//                case R.id.menu_home:
//                    selectedFragment = new HomeFragment();
//                    title = "Monitoring Status";
//                    break;
//                case R.id.menu_history:
//                    selectedFragment = new HistoryFragment();
//                    title = "History";
//                    break;
//                case R.id.menu_info:
//                    selectedFragment = new InfoFragment();
//                    title = "Info App";
//                    break;
//            }
//            titleActivity.setText(title);
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, selectedFragment).commit();
//            return true;
//        });
        navigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            String title = null;

            switch (item.getItemId()) {
                case R.id.menu_home:
                    selectedFragment = new HomeFragment();
                    title = "Monitoring Status";
                    break;
                case R.id.menu_history:
                    selectedFragment = new HistoryFragment();
                    title = "History";
                    break;
                case R.id.menu_info:
                    selectedFragment = new InfoFragment();
                    title = "Info App";
                    break;
            }
            titleActivity.setText(title);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, selectedFragment).commit();
            return true;
        });
        navigationView.setOnItemReselectedListener(item -> {
            Fragment selectedFragment = null;
            String title = null;

            switch (item.getItemId()) {
                case R.id.menu_home:
                    selectedFragment = new HomeFragment();
                    title = "Monitoring Status";
                    break;
                case R.id.menu_history:
                    selectedFragment = new HistoryFragment();
                    title = "History";
                    break;
                case R.id.menu_info:
                    selectedFragment = new InfoFragment();
                    title = "Info App";
                    break;
            }
            titleActivity.setText(title);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, selectedFragment).commit();
        });
    }

    public boolean checkInternet() {
        boolean connectStatus = true;
        ConnectivityManager ConnectionManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            ConnectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            connectStatus = true;
        } else {
            connectStatus = false;
        }
        return connectStatus;
    }
}