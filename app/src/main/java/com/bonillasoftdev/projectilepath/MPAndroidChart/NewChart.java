package com.bonillasoftdev.projectilepath.MPAndroidChart;

import com.bonillasoftdev.projectilepath.Model.DataModel;
import com.bonillasoftdev.projectilepath.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.ArrayList;

public class NewChart extends AsyncTask<Void, Void, Void> {
    DataModel data;
    private LineChart chart;
    private double initialAngle;
    private double initialVelocity;
    private double gravity;
    private double radians;
    private double maxRange;
    private Context context;
    private ProgressDialog progressDialog;

    public NewChart(DataModel data, LineChart chart, double maxRange, Context context) {
        this.context = context;
        this.data = data;
        this.chart = chart;
        this.maxRange = maxRange;
        this.initialVelocity = data.getInitialVelocity();
        this.initialAngle = data.getInitialAngle();
        this.gravity = data.getGravity();
        radians= Math.toRadians(data.getInitialAngle());

        progressDialog = new ProgressDialog(context);
        progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Calculando...");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    private void createChart(){
        ArrayList<Entry> entries = new ArrayList<>();


        for (int x = 0; x <= maxRange; x++) {

            // f(x) = tg(53.1°) x - ((9.8) / (2 * 37^(2) * cos^(2)(53°))) * x^2
            double yDouble = (double) Math.tan(radians) * x - (gravity / (2 * Math.pow(initialVelocity, 2) * Math.pow(Math.cos(radians), 2))) * Math.pow(x, 2);
            float y = (float) yDouble;
            if (y >= -1) {
                entries.add(new Entry(x, y));
            }
        }

        LineDataSet dataSet = new LineDataSet(entries, "Parábola");
        dataSet.setColor(Color.RED);
        dataSet.setLineWidth(2f);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        LineData lineData = new LineData(dataSets);

        chart.setData(lineData);
        chart.getXAxis().setDrawLabels(true);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getLegend().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        ValueFormatter xAxisFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf(value);
            }
        };
        xAxis.setValueFormatter(xAxisFormatter);

        Description description = new Description();
        description.setText(data.getName());
        chart.setDescription(description);

        chart.invalidate();

    }


    @Override
    protected Void doInBackground(Void... voids) {
        createChart();
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        progressDialog.dismiss();
    }
}
