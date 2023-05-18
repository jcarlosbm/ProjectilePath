package com.bonillasoftdev.projectilepath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bonillasoftdev.projectilepath.MPAndroidChart.NewChart;
import com.bonillasoftdev.projectilepath.Model.DataModel;
import com.bonillasoftdev.projectilepath.Model.GeneralResultModel;
import com.bonillasoftdev.projectilepath.Model.TimeResultModel;
import com.github.mikephil.charting.charts.LineChart;

public class CalculateActivity extends AppCompatActivity {
    LineChart lc_chart;
    TextView tv_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        lc_chart = (LineChart) findViewById(R.id.lc_chart);
        tv_test = (TextView) findViewById(R.id.tv_test);

        DataModel dataModel = (DataModel) getIntent().getSerializableExtra("dataModel");
        GeneralResultModel generalResultModel = (GeneralResultModel) getIntent().getSerializableExtra("generalResultModel");
        TimeResultModel timeResultModel = (TimeResultModel) getIntent().getSerializableExtra("timeResultModel");
        Toast.makeText(this, "velocidad: " + dataModel.getInitialVelocity(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Tiempo de vuelo: " + generalResultModel.getFlightTime(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Rapidex: " + timeResultModel.getCalculateSpeed(), Toast.LENGTH_SHORT).show();

        Log.i("test", "Comenzamos el test");
        Log.i("Velocidad inicial de:", "" + dataModel.getInitialVelocity());
        Log.i("Ángulo inicial de:", "" + dataModel.getInitialAngle());
        Log.i("Gravedad: ", ""+ dataModel.getGravity());
        Log.i("Tiempo: ", "" + dataModel.getTime());


        Log.i("Componente en VoX", "" + generalResultModel.getVelocityComponentInX());
        Log.i("Componente en VoY", "" + generalResultModel.getVelocityComponentInY());
        Log.i("Posición en X a los 2.00s", "" + timeResultModel.getCalculatePositionInX());
        Log.i("Posición en Y a los 2.00s", "" + timeResultModel.getCalculatePositionInY());
        Log.i("La altura máxima del proyectil es de:", "" + generalResultModel.getMaximumHeight());
        Log.i("El tiempo total de vuelo es de:", "" + generalResultModel.getFlightTime());
        Log.i("El alcance máximo es de:", "" + generalResultModel.getMaximumRange());
        Log.i("Tiempo de subida:", "" + generalResultModel.getRiseTime());
        Log.i("Velocidad en X a los 2.00s", "" + timeResultModel.getCalculateHorizontalVelocity());
        Log.i("Velocidad en Y a los 2.00s", "" + timeResultModel.getCalculateVerticalVelocity());
        Log.i("Distancia recorrida a los 2.00s", "" + timeResultModel.getCalculateDistanceToTheOrigin());
        Log.i("Rapidez a los 2.00s", "" + timeResultModel.getCalculateSpeed());
        Log.i("Ángulo a los 2.00s", "" + timeResultModel.getCalculateAngle());

        String test = "Datos: \n" +
                "Componente en VoX: " + generalResultModel.getVelocityComponentInX() + "m/s\n" +
                "Componente en VoY: " + generalResultModel.getVelocityComponentInY() + "m/s \n" +
                "La altura máxima del proyectil es de: " + generalResultModel.getMaximumHeight() + "m\n" +
                "El alcance máximo es de: " + + generalResultModel.getMaximumRange() + "m\n" +
                "Tiempo de subida: " + generalResultModel.getRiseTime() + "s\n" +
                "El tiempo total de vuelo es de: " + + generalResultModel.getFlightTime() + "s\n"
        ;

        tv_test.setText(test);


        NewChart newChart = new NewChart(dataModel, lc_chart, generalResultModel.getMaximumRange());
        newChart.createChart();
    }
}