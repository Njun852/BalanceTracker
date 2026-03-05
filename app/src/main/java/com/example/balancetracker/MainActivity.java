package com.example.balancetracker;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LineChart lineChart = findViewById(R.id.lineChart);
        lineChart.getDescription().setEnabled(false);
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0, 100));
        entries.add(new Entry(1, 300));
        entries.add(new Entry(2, 150));
        entries.add(new Entry(3, 400));
        entries.add(new Entry(4, 400));
        entries.add(new Entry(5, 400));
        entries.add(new Entry(6, 200));

        ArrayList<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0, 100));
        entries2.add(new Entry(1, 200));
        entries2.add(new Entry(2, 450));
        entries2.add(new Entry(3, 100));
        entries2.add(new Entry(4, 600));
        entries2.add(new Entry(5, 300));
        entries2.add(new Entry(6, 800));

        LineDataSet ds2 = new LineDataSet(entries2, "income");
        ds2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        ds2.setCircleColor(Color.parseColor("#49E673"));
        ds2.setColor(Color.parseColor("#49E673"));
        ds2.setCircleRadius(5f);
        ds2.setLineWidth(2f);
        ds2.setCircleRadius(5f);
        ds2.setLineWidth(2f);
        ds2.setDrawValues(false);

        LineDataSet ds = new LineDataSet(entries, "expenses");
        ds.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        ds.setCircleColor(Color.parseColor("#FF9644"));
        ds.setColor(Color.parseColor("#FF9644"));
        ds.setCircleRadius(5f);
        ds.setLineWidth(2f);


        String[] days = new String[] {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        ds.setDrawValues(false);
        LineData ld = new LineData();
        ld.addDataSet(ds);
        ld.addDataSet(ds2);
        lineChart.setData(ld);
        lineChart.getLegend().setTextColor(Color.WHITE);
        lineChart.setBackgroundColor(Color.BLACK);
        lineChart.invalidate();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<HistoryItem> items = new ArrayList<>();
        items.add(new HistoryItem("Food", "Expense", 400, new Date()));
        items.add(new HistoryItem("Monthly Salary", "Income", 5000, new Date()));
        items.add(new HistoryItem("Wifi", "Expense", 1200, new Date()));
        items.add(new HistoryItem("Repairs", "Expense", 700, new Date()));
        recyclerView.setAdapter(new HistoryItemAdapter(getApplicationContext(), items));
    }
}