package com.example.pruebaexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(1,420));
        visitors.add(new BarEntry(2,475));
        visitors.add(new BarEntry(3,508));
        visitors.add(new BarEntry(4,660));
        visitors.add(new BarEntry(5,550));
        visitors.add(new BarEntry(6,630));

        BarDataSet barDataSet = new BarDataSet(visitors, "Powers");
        barDataSet.setColors(Color.MAGENTA);

        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText(".");
        barChart.animateY(1500);
    }
}