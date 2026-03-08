package com.example.balancetracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.balancetracker.HistoryItem.HistoryItem;
import com.example.balancetracker.HistoryItem.HistoryItemAdapter;
import com.example.balancetracker.HistoryItem.HistoryItemType;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    HistoryViewModel historyViewModel;
    RecyclerView recyclerView;
    LineChart lineChart;
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

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        historyViewModel.getHistoryItems().observe(this, state ->{
            displayAnalytics(state);
            recyclerView.setAdapter(new HistoryItemAdapter(getApplicationContext(), state));
        });

        CardView incomeCVBtn = findViewById(R.id.incomeCVBtn);
        CardView expenseCVBtn = findViewById(R.id.expenseCVBtn);
        incomeCVBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, IncomeActivity.class);
            startActivity(intent);
        });
        expenseCVBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExpenseActivity.class);
            startActivity(intent);
        });
    }

    private void displayAnalytics(ArrayList<HistoryItem> items) {
        lineChart = findViewById(R.id.lineChart);
        lineChart.getDescription().setEnabled(false);

        //date range
        String[] days = new String[7];
        LocalDateTime today = LocalDateTime.now();
        HashMap<String, Integer> incomeRange = new HashMap<>();
        HashMap<String, Integer> expenseRange = new HashMap<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String[] keys = new String[7];
        for(int i = days.length-1; i >= 0; i--) {
            int daysToSec = 86400;
            LocalDateTime t = LocalDateTime.ofEpochSecond(today.toEpochSecond(ZoneOffset.UTC)-((long) daysToSec *(days.length-i-1)), 0, ZoneOffset.UTC);
            incomeRange.put(t.format(dateTimeFormatter), 0);
            expenseRange.put(t.format(dateTimeFormatter), 0);
            keys[i] = t.format(dateTimeFormatter);
            days[i] = String.valueOf(t.getDayOfMonth());
        }
        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);

        int totalBudget = 0;
        for(int i = 0; i < items.size(); i++) {
            HistoryItem item = items.get(i);
            String key = item.getDate().format(dateTimeFormatter);
            Integer expenseForDay = expenseRange.get(key);
            Integer incomeForDay = incomeRange.get(key);
            if(expenseForDay != null && item.getType() == HistoryItemType.EXPENSE) {
                expenseRange.put(key, expenseForDay+item.getAmount());
            }else if(incomeForDay != null  && item.getType() == HistoryItemType.INCOME) {
                incomeRange.put(key, incomeForDay+item.getAmount());
            }
            int sign = item.getType() == HistoryItemType.INCOME ? 1 : -1;
            totalBudget += item.getAmount()*sign;
        }
        TextView totalBudgetTxt = findViewById(R.id.totalBudgetTxt);
        totalBudgetTxt.setText(String.format("₱%,d", totalBudget));
        ArrayList<Entry> expenses = new ArrayList<>();
        ArrayList<Entry> income = new ArrayList<>();
        for(int i = 0; i < keys.length; i++) {
            Integer expenseAmount = expenseRange.get(keys[i]);
            Integer incomeAmount = incomeRange.get(keys[i]);
            if(expenseAmount != null) {
                expenses.add(new Entry(i, expenseAmount));
            }
            if(incomeAmount != null) {
                income.add(new Entry(i, incomeAmount));
            }
        }

        LineDataSet ds = new LineDataSet(expenses, "expenses");
        ds.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        ds.setCircleColor(Color.parseColor("#FF9644"));
        ds.setColor(Color.parseColor("#FF9644"));
        ds.setCircleRadius(5f);
        ds.setLineWidth(2f);
        ds.setDrawValues(false);

        LineDataSet ds2 = new LineDataSet(income, "income");
        ds2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        ds2.setCircleColor(Color.parseColor("#49E673"));
        ds2.setColor(Color.parseColor("#49E673"));
        ds2.setCircleRadius(5f);
        ds2.setLineWidth(2f);
        ds2.setCircleRadius(5f);
        ds2.setLineWidth(2f);
        ds2.setDrawValues(false);

        LineData ld = new LineData();
        ld.addDataSet(ds);
        ld.addDataSet(ds2);
        lineChart.setData(ld);

        lineChart.getLegend().setTextColor(Color.WHITE);
        lineChart.setBackgroundColor(Color.BLACK);
        lineChart.invalidate();
    }
}