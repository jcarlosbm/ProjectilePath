package com.bonillasoftdev.projectilepath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {
    private ProgressBar pb_loading_splash;
    private ImageView iv_logo;
    private LinearLayout ll_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String mode = sharedPreferences.getString("dark_mode", "0");


        pb_loading_splash = (ProgressBar) findViewById(R.id.pb_loading_splash);
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        ll_splash = (LinearLayout) findViewById(R.id.ll_splash);
        toggleTheme(mode);

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {

                    sleep(3000);

                    pb_loading_splash.setProgress(100);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    private void toggleTheme(String mode) {
        if (mode.equals("1")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            iv_logo.setImageResource(R.drawable.img_logo_dark);
            ll_splash.setBackgroundColor(getResources().getColor(R.color.background_splash_dark_color));
        }

    }
}