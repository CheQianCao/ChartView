package com.geyek.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.geyek.widget.diagram.GeyekChartView;
import com.geyek.widget.diagram.chart.LineChart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GeyekChartView mCharView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharView = (GeyekChartView) findViewById(R.id.chartview);
        /*mCharView.setBackgroundColor(Color.DKGRAY);*/

        /*CurveChart curveChart = new CurveChart(mCharView);
        curveChart.setMaxItem(10);
        curveChart.setLineColor(Color.RED);
        curveChart.setLineWidth(5);
        curveChart.setMaxValue(50);
        curveChart.setMinValue(0);
        curveChart.setPoint(0F);
        curveChart.setPoint(10F);
        curveChart.setPoint(50F);
        curveChart.setPoint(30F);
        curveChart.setPoint(40F);
        mCharView.setChart(curveChart);*/

        final LineChart lineChart = new LineChart(mCharView);
        lineChart.setMaxItem(10);
        lineChart.setLineWidth(5);
        lineChart.setLineColor(Color.RED);
        lineChart.setMaxValue(80);
        lineChart.setMinValue(70);
        lineChart.setPoint(70F);
        lineChart.setPoint(80F);
        lineChart.setPoint(90F);
        lineChart.setPoint(75F);
        lineChart.setPoint(60F);
        lineChart.setPoint(70F);
        lineChart.setPoint(72F);
        lineChart.setPoint(90F);
        lineChart.setPoint(-10F);
        lineChart.setPoint(3F);
        lineChart.setPoint(76F);
        mCharView.setChart(lineChart);
    }
}
