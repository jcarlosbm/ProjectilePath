package com.bonillasoftdev.projectilepath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {
    private RadioGroup rg_radio_group_theme;
    private TextView tv_decimals;
    private Switch s_scientific_notation, s_dark_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        tv_decimals = (TextView) findViewById(R.id.tv_decimals);
        s_scientific_notation = (Switch) findViewById(R.id.s_scientific_notation);
        s_dark_mode = (Switch) findViewById(R.id.s_dark_mode);

        loadSettings();

        s_dark_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("dark_mode", "0");
                    editor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("dark_mode", "1");
                    editor.apply();
                }
                recreate();
            }
        });
    }

    public void loadSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String mode = sharedPreferences.getString("dark_mode", "0");

        if (mode.equals("1")){
            s_dark_mode.setChecked(true);
        }
    }
}