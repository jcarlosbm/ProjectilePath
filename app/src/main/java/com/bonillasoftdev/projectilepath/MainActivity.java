package com.bonillasoftdev.projectilepath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bonillasoftdev.projectilepath.Equations.GeneralEquations;
import com.bonillasoftdev.projectilepath.Equations.TimeEquations;
import com.bonillasoftdev.projectilepath.Model.DataModel;
import com.bonillasoftdev.projectilepath.Model.GeneralResultModel;
import com.bonillasoftdev.projectilepath.Model.TimeResultModel;

public class MainActivity extends AppCompatActivity {
    Button btn_calculate, btn_record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_calculate = (Button) findViewById(R.id.btn_calculate);
        btn_record = (Button) findViewById(R.id.btn_record);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DataModel data = new DataModel(37.0, 53.1, 2, 9.8);
//
//                GeneralEquations generalEquations = new GeneralEquations(data);
//                GeneralResultModel resultModel = generalEquations.getGeneralResultModel();
//
//                TimeEquations timeEquations = new TimeEquations(data, resultModel);
//                TimeResultModel timeResultModel = timeEquations.getTimeResultModel();
//
//
                Intent intent = new Intent(getApplicationContext(), InsertDataActivity.class);
                startActivity(intent);

            }
        });

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Se implementará en futuras actualizaciones ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}