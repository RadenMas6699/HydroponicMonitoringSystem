package com.radenmas.system.monitoring.hydroponic.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.radenmas.system.R;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.HistoryFragment;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.HomeFragment;
import com.radenmas.system.monitoring.hydroponic.ui.fragment.InfoFragment;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView titleActivity = findViewById(R.id.titleActivity);

        titleActivity.setText("Monitoring Status");
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new HomeFragment()).commit();

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
}