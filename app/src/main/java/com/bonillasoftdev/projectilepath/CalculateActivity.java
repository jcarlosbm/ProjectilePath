package com.bonillasoftdev.projectilepath;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bonillasoftdev.projectilepath.Equations.TimeEquations;
import com.bonillasoftdev.projectilepath.MPAndroidChart.NewChart;
import com.bonillasoftdev.projectilepath.Model.DataModel;
import com.bonillasoftdev.projectilepath.Model.GeneralResultModel;
import com.bonillasoftdev.projectilepath.Model.TimeResultModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class CalculateActivity extends Activity implements OnChartValueSelectedListener{
    private LineChart lc_chart;
    private TextView tv_title, tv_positionInX, tv_positionInY, tv_verticalVelocity, tv_horizontalVelocity;
    private TextView tv_speed, tv_distanceToTheOrigin, tv_angle, tv_velocityComponentInX, tv_velocityComponentInY;
    private TextView tv_riseTime, tv_flightTime, tv_maximumRange, tv_maximumHeight;
    private  DataModel dataModel;
    private  GeneralResultModel generalResultModel;
    private  TimeResultModel timeResultModel;
    String decimals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        decimals = sharedPreferences.getString("decimals", "2");

        lc_chart = (LineChart) findViewById(R.id.lc_chart);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_positionInX = (TextView) findViewById(R.id.tv_positionInX);
        tv_positionInY = (TextView) findViewById(R.id.tv_positionInY);
        tv_verticalVelocity = (TextView) findViewById(R.id.tv_verticalVelocity);
        tv_horizontalVelocity = (TextView) findViewById(R.id.tv_horizontalVelocity);
        tv_speed = (TextView) findViewById(R.id.tv_speed);
        tv_distanceToTheOrigin = (TextView) findViewById(R.id.tv_distanceToTheOrigin);
        tv_angle = (TextView) findViewById(R.id.tv_angle);
        tv_velocityComponentInX = (TextView) findViewById(R.id.tv_velocityComponentInX);
        tv_velocityComponentInY = (TextView) findViewById(R.id.tv_velocityComponentInY);
        tv_riseTime = (TextView) findViewById(R.id.tv_riseTime);
        tv_flightTime = (TextView) findViewById(R.id.tv_flightTime);
        tv_maximumRange = (TextView) findViewById(R.id.tv_maximumRange);
        tv_maximumHeight = (TextView) findViewById(R.id.tv_maximumHeight);

        dataModel = (DataModel) getIntent().getSerializableExtra("dataModel");
        generalResultModel = (GeneralResultModel) getIntent().getSerializableExtra("generalResultModel");
        timeResultModel = (TimeResultModel) getIntent().getSerializableExtra("timeResultModel");

        Log.i("Velocidad inicial de:", "" + dataModel.getInitialVelocity());
        Log.i("Ángulo inicial de:", "" + dataModel.getInitialAngle());
        Log.i("Gravedad: ", ""+ dataModel.getGravity());
        Log.i("Tiempo: ", "" + dataModel.getTime());



        tv_velocityComponentInX.setText("Componente en VoX: " + (Double.parseDouble(String.format("%." + decimals + "f",generalResultModel.getVelocityComponentInX()))) + "m/s");
        tv_velocityComponentInY.setText("Componente en VoY: " + (Double.parseDouble(String.format("%." + decimals + "f",generalResultModel.getVelocityComponentInY()))) + "m/s");
        tv_maximumHeight.setText("La altura máxima del proyectil es de: " + (Double.parseDouble(String.format("%." + decimals + "f",generalResultModel.getMaximumHeight()))) + "m");
        tv_flightTime.setText("El tiempo total de vuelo es de: " + (Double.parseDouble(String.format("%." + decimals + "f",generalResultModel.getMaximumHeight()))) + "s");
        tv_maximumRange.setText("El alcance máximo es de: " + (Double.parseDouble(String.format("%." + decimals + "f",generalResultModel.getMaximumRange()))) + "m");
        tv_riseTime.setText("Tiempo de subida: " + (Double.parseDouble(String.format("%." + decimals + "f",generalResultModel.getRiseTime()))) + "s");


        NewChart newChart = new NewChart(dataModel, lc_chart, generalResultModel.getMaximumRange(), CalculateActivity.this);
        newChart.execute();

        lc_chart.setOnChartValueSelectedListener((OnChartValueSelectedListener) this);


    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onValueSelected(Entry entry, Highlight highlight) {

        double time = entry.getX()/generalResultModel.getVelocityComponentInX();
        dataModel.setTime(time);
        TimeResultModel timeResultModelNew = createTimeResultModel(dataModel, generalResultModel);

        tv_title.setText("Datos para: " + (Double.parseDouble(String.format("%." + decimals + "f",time))) + "s" );
        tv_positionInX.setText("Posición en X: " + (Double.parseDouble(String.format("%." + decimals + "f",timeResultModelNew.getCalculatePositionInX()))) + "m");
        tv_positionInY.setText("Posición en Y: " + (Double.parseDouble(String.format("%." + decimals + "f",timeResultModelNew.getCalculatePositionInY()))) + "m");
        tv_horizontalVelocity.setText("Velocidad en X: " + (Double.parseDouble(String.format("%." + decimals + "f",timeResultModelNew.getCalculateHorizontalVelocity()))) + "s");
        tv_verticalVelocity.setText("Velocidad en Y: " + (Double.parseDouble(String.format("%." + decimals + "f",timeResultModelNew.getCalculateVerticalVelocity()))) + "s");
        tv_distanceToTheOrigin.setText("Distancia recorrida: " + (Double.parseDouble(String.format("%." + decimals + "f",timeResultModelNew.getCalculateDistanceToTheOrigin()))) + "m");
        tv_speed.setText("Rapidez: " + (Double.parseDouble(String.format("%." + decimals + "f",timeResultModelNew.getCalculateSpeed()))) + "m/s");
        tv_angle.setText("Ángulo: " + (Double.parseDouble(String.format("%." + decimals + "f",timeResultModelNew.getCalculateAngle()))) + "grados");
    }

    @Override
    public void onNothingSelected() {

    }

    public TimeResultModel createTimeResultModel(DataModel data, GeneralResultModel resultModel){
        TimeEquations timeEquations = new TimeEquations(data, resultModel);
        return timeEquations.getTimeResultModel();
    }
}




