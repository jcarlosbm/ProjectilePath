package com.bonillasoftdev.projectilepath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bonillasoftdev.projectilepath.Equations.GeneralEquations;
import com.bonillasoftdev.projectilepath.Equations.TimeEquations;
import com.bonillasoftdev.projectilepath.Model.DataModel;
import com.bonillasoftdev.projectilepath.Model.GeneralResultModel;
import com.bonillasoftdev.projectilepath.Model.TimeResultModel;

import java.io.Serializable;

public class InsertDataActivity extends AppCompatActivity {
    EditText et_exercise_id, et_initial_velocity, et_initial_angle, et_gravity;
    Button btn_calculate_equation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        et_exercise_id = (EditText) findViewById(R.id.et_exercise_id);
        et_initial_velocity = (EditText) findViewById(R.id.et_initial_velocity);
        et_initial_angle = (EditText) findViewById(R.id.et_initial_angle);
        et_gravity = (EditText) findViewById(R.id.et_gravity);
        btn_calculate_equation = (Button) findViewById(R.id.btn_calculate_equation);

        btn_calculate_equation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_exercise_id.getText().toString();
                String velocity = et_initial_velocity.getText().toString();
                String angle = et_initial_angle.getText().toString();
                String gravity = et_gravity.getText().toString();

                if (!id.equals("") && !id.isEmpty()){
                    if (!velocity.equals("") && !velocity.isEmpty()){
                        if (!angle.equals("") && !angle.isEmpty() && Double.parseDouble(angle) >= 0 && Double.parseDouble(angle) <= 360 ){
                            if (!gravity.equals("") && !gravity.isEmpty()){

                                DataModel dataModel = new DataModel(id, Double.parseDouble(velocity), Double.parseDouble(angle), Double.parseDouble(gravity));
                                GeneralResultModel generalResultModel = createGeneralResultModel(dataModel);
                                TimeResultModel timeResultModel = createTimeResultModel(dataModel, generalResultModel);

                                Intent intent = new Intent(getApplicationContext(), CalculateActivity.class);
                                intent.putExtra("dataModel", (Serializable) dataModel);
                                intent.putExtra("generalResultModel", (Serializable) generalResultModel);
                                intent.putExtra("timeResultModel", (Serializable) timeResultModel);
                                startActivity(intent);

                            }else {
                                et_gravity.setError("Debe proporcionar una gravedad válida");
                            }
                        }else {
                            et_initial_angle.setError("Debe proporcionar un ángulo válido");
                        }

                    }else {
                        et_initial_velocity.setError("Debe proporcionar una velocidad válida");
                    }
                }else {
                    et_exercise_id.setError("Debe proporcionar un identificador válido");
                }
            }
        });

    }

    public GeneralResultModel createGeneralResultModel(DataModel data){
        GeneralEquations generalEquations = new GeneralEquations(data);
        return generalEquations.getGeneralResultModel();
    }

    public TimeResultModel createTimeResultModel(DataModel data, GeneralResultModel resultModel){
        TimeEquations timeEquations = new TimeEquations(data, resultModel);
        return timeEquations.getTimeResultModel();
    }
}