package com.bonillasoftdev.projectilepath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
                DataModel data = new DataModel(37.0, 53.1, 2, 9.8);

                GeneralEquations generalEquations = new GeneralEquations(data);
                GeneralResultModel resultModel = generalEquations.getGeneralResultModel();

                TimeEquations timeEquations = new TimeEquations(data, resultModel);
                TimeResultModel timeResultModel = timeEquations.getTimeResultModel();

                Log.i("test", "Comenzamos el test");
                Log.i("Velocidad inicial de:", "" + data.getInitialVelocity());
                Log.i("Ángulo inicial de:", "" + data.getInitialAngle());
                Log.i("Gravedad: ", ""+ data.getGravity());
                Log.i("Tiempo: ", "" + data.getTime());


                Log.i("Componente en VoX", "" + resultModel.getVelocityComponentInX());
                Log.i("Componente en VoY", "" + resultModel.getVelocityComponentInY());
                Log.i("Posición en X a los 2.00s", "" + timeResultModel.getCalculatePositionInX());
                Log.i("Posición en Y a los 2.00s", "" + timeResultModel.getCalculatePositionInY());
                Log.i("La altura máxima del proyectil es de:", "" + resultModel.getMaximumHeight());
                Log.i("El tiempo total de vuelo es de:", "" + resultModel.getFlightTime());
                Log.i("El alcance máximo es de:", "" + resultModel.getMaximumRange());
                Log.i("Tiempo de subida:", "" + resultModel.getRiseTime());
                Log.i("Velocidad en X a los 2.00s", "" + timeResultModel.getCalculateHorizontalVelocity());
                Log.i("Velocidad en Y a los 2.00s", "" + timeResultModel.getCalculateVerticalVelocity());
                Log.i("Distancia recorrida a los 2.00s", "" + timeResultModel.getCalculateDistanceToTheOrigin());
                Log.i("Rapidez a los 2.00s", "" + timeResultModel.getCalculateSpeed());
                Log.i("Ángulo a los 2.00s", "" + timeResultModel.getCalculateAngle());

            }
        });

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}