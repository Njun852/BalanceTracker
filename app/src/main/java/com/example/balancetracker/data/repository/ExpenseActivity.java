package com.example.balancetracker.data.repository;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.balancetracker.HistoryItem.HistoryItem;
import com.example.balancetracker.HistoryItem.HistoryItemType;
import com.example.balancetracker.HistoryViewModel;
import com.example.balancetracker.R;

import java.time.LocalDateTime;

public class ExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expense);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView expenseCVBtn = findViewById(R.id.expenseCVBtn);
        HistoryViewModel viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        expenseCVBtn.setOnClickListener(v ->{
            EditText descriptionTxt = findViewById(R.id.descriptionTxt);
            EditText amountTxt = findViewById(R.id.amountTxt);
            int amount = Integer.parseInt(amountTxt.getText().toString());
            HistoryItem item = new HistoryItem(descriptionTxt.getText().toString(), HistoryItemType.EXPENSE, amount, LocalDateTime.now());
            viewModel.addHistoryItem(item);
            finish();
        });
    }
}