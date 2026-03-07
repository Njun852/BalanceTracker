package com.example.balancetracker.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.balancetracker.HistoryItem.HistoryItem;
import com.example.balancetracker.HistoryItem.HistoryItemType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HistoryRepo {
    private static HistoryRepo instance;
    private final MutableLiveData<ArrayList<HistoryItem>> historyItems = new MutableLiveData<>();

    private HistoryRepo() {
        historyItems.setValue(new ArrayList<>());
        this.addHistoryItem(new HistoryItem("Food", HistoryItemType.EXPENSE, 400, LocalDateTime.of(2026, 3, 4, 15, 12)));
        this.addHistoryItem(new HistoryItem("Monthly Salary", HistoryItemType.INCOME, 5000, LocalDateTime.of(2026, 3, 4, 9, 12)));
        this.addHistoryItem(new HistoryItem("Wifi", HistoryItemType.EXPENSE, 1200, LocalDateTime.of(2026, 3, 6, 10, 32)));
        this.addHistoryItem(new HistoryItem("Repairs", HistoryItemType.EXPENSE, 700, LocalDateTime.of(2026, 3, 1, 12, 30)));
    }

    public static synchronized HistoryRepo getInstance() {
        if(instance == null) {
            instance = new HistoryRepo();
        }
        return instance;
    }

    public LiveData<ArrayList<HistoryItem>> getHistoryItems() {
        return historyItems;
    }
    public void addHistoryItem(HistoryItem item) {
        ArrayList<HistoryItem> items = historyItems.getValue();
        if(items != null) {
            items.add(item);
            historyItems.setValue(items);
        }
    }
}
