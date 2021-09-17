package com.radenmas.hydroponicmonitoringsystem.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.radenmas.hydroponicmonitoringsystem.R;
import com.radenmas.hydroponicmonitoringsystem.ui.fragment.HomeFragment;
import com.radenmas.hydroponicmonitoringsystem.ui.fragment.InfoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView titleActivity = findViewById(R.id.titleActivity);

        titleActivity.setText("Monitoring Status");
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new HomeFragment()).commit();

        BottomNavigationView navigationView = findViewById(R.id.nav_bottom);
        navigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            String title = null;

            switch (item.getItemId()) {
                case R.id.menu_home:
                    selectedFragment = new HomeFragment();
                    title = "Monitoring Status";
                    break;
                case R.id.menu_history:
                    selectedFragment = new InfoFragment();
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
    }
}