package com.bonillasoftdev.projectilepath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {
    private RadioGroup rg_radio_group_theme;
    private Spinner sp_decimals;
    private Switch s_scientific_notation, s_dark_mode;
    String[] decimals = {"1","2","3","4","5","6","7","8","9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, decimals);


        sp_decimals = (Spinner) findViewById(R.id.sp_decimals);
        s_scientific_notation = (Switch) findViewById(R.id.s_scientific_notation);
        s_dark_mode = (Switch) findViewById(R.id.s_dark_mode);


        sp_decimals.setAdapter(adapter);

        sp_decimals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("decimals", item);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




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
        String decimals = sharedPreferences.getString("decimals", "2");

        if (mode.equals("1")){
            s_dark_mode.setChecked(true);
        }

        sp_decimals.setSelection(Integer.parseInt(decimals) - 1);
    }
}