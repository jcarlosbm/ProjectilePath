package com.bonillasoftdev.projectilepath;

import androidx.appcompat.app.AppCompatActivity;

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

public class CalculateActivity extends AppCompatActivity implements OnChartValueSelectedListener{
    private LineChart lc_chart;
    private TextView tv_title, tv_positionInX, tv_positionInY, tv_verticalVelocity, tv_horizontalVelocity;
    private TextView tv_speed, tv_distanceToTheOrigin, tv_angle, tv_velocityComponentInX, tv_velocityComponentInY;
    private TextView tv_riseTime, tv_flightTime, tv_maximumRange, tv_maximumHeight;
    private  DataModel dataModel;
    private  GeneralResultModel generalResultModel;
    private  TimeResultModel timeResultModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

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
                "El tiempo total de vuelo es de: " + generalResultModel.getFlightTime() + "s\n"
        ;

        tv_velocityComponentInX.setText("Componente en VoX: " + generalResultModel.getVelocityComponentInX() + "m/s");
        tv_velocityComponentInY.setText("Componente en VoY: " + generalResultModel.getVelocityComponentInY() + "m/s");
        tv_maximumHeight.setText("La altura máxima del proyectil es de: " + generalResultModel.getMaximumHeight() + "m");
        tv_flightTime.setText("El tiempo total de vuelo es de: " + generalResultModel.getMaximumHeight() + "s");
        tv_maximumRange.setText("El alcance máximo es de: " + generalResultModel.getMaximumRange() + "m");
        tv_riseTime.setText("Tiempo de subida: " + generalResultModel.getRiseTime() + "s");


        NewChart newChart = new NewChart(dataModel, lc_chart, generalResultModel.getMaximumRange());
        newChart.createChart();

        lc_chart.setOnChartValueSelectedListener((OnChartValueSelectedListener) this);


    }
    @Override
    public void onValueSelected(Entry entry, Highlight highlight) {

        double time = entry.getX()/generalResultModel.getVelocityComponentInX();
        dataModel.setTime(time);
        TimeResultModel timeResultModelNew = createTimeResultModel(dataModel, generalResultModel);

        tv_title.setText("Datos para: " + time + "s" );
        tv_positionInX.setText("Posición en X: " + timeResultModelNew.getCalculatePositionInX() + "m");
        tv_positionInY.setText("Posición en Y: " + timeResultModelNew.getCalculatePositionInY() + "m");
        tv_horizontalVelocity.setText("Velocidad en X: " + timeResultModelNew.getCalculateHorizontalVelocity() + "s");
        tv_verticalVelocity.setText("Velocidad en Y: " + timeResultModelNew.getCalculateVerticalVelocity() + "s");
        tv_distanceToTheOrigin.setText("Distancia recorrida: " + timeResultModelNew.getCalculateDistanceToTheOrigin() + "m");
        tv_speed.setText("Rapidez: " + timeResultModelNew.getCalculateSpeed() + "m/s");
        tv_angle.setText("Ángulo: " + timeResultModelNew.getCalculateAngle() + "grados");
    }

    @Override
    public void onNothingSelected() {

    }

    public TimeResultModel createTimeResultModel(DataModel data, GeneralResultModel resultModel){
        TimeEquations timeEquations = new TimeEquations(data, resultModel);
        return timeEquations.getTimeResultModel();
    }
}




