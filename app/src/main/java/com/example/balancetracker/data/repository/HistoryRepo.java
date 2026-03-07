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
        this.addHistoryItem(new HistoryItem("Monthly Salary", HistoryItemType.INCOME, 5000, LocalDateTime.of(2026, 3, 1, 9, 0)));
        this.addHistoryItem(new HistoryItem("Apartment Rent", HistoryItemType.EXPENSE, 1200, LocalDateTime.of(2026, 3, 1, 10, 30)));
        this.addHistoryItem(new HistoryItem("Grocery Shopping", HistoryItemType.EXPENSE, 150, LocalDateTime.of(2026, 3, 2, 18, 15)));
        this.addHistoryItem(new HistoryItem("Fuel Refill", HistoryItemType.EXPENSE, 60, LocalDateTime.of(2026, 3, 3, 8, 45)));
        this.addHistoryItem(new HistoryItem("Freelance Bonus", HistoryItemType.INCOME, 450, LocalDateTime.of(2026, 3, 4, 14, 0)));
        this.addHistoryItem(new HistoryItem("Starbucks Coffee", HistoryItemType.EXPENSE, 15, LocalDateTime.of(2026, 3, 4, 15, 30)));
        this.addHistoryItem(new HistoryItem("Internet Bill", HistoryItemType.EXPENSE, 80, LocalDateTime.of(2026, 3, 5, 10, 0)));
        this.addHistoryItem(new HistoryItem("Friday Night Pizza", HistoryItemType.EXPENSE, 45, LocalDateTime.of(2026, 3, 6, 20, 0)));
        this.addHistoryItem(new HistoryItem("Gym Subscription", HistoryItemType.EXPENSE, 50, LocalDateTime.of(2026, 3, 7, 11, 20)));
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
