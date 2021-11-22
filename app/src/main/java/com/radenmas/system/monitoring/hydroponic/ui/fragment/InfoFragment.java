package com.radenmas.system.monitoring.hydroponic.ui.fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.radenmas.system.monitoring.hydroponic.R;


public class InfoFragment extends Fragment {
    TextView app_version, desc_app;

    public InfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_app, container, false);

        Context context = getContext();

        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info.versionName;

        app_version = view.findViewById(R.id.app_version);
        app_version.setText("Version " + version);

        desc_app = view.findViewById(R.id.desc_app);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            desc_app.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }


        return view;
    }
}