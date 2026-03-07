package com.example.balancetracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.balancetracker.HistoryItem.HistoryItem;
import com.example.balancetracker.HistoryItem.HistoryItemType;
import com.example.balancetracker.data.repository.HistoryRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HistoryViewModel extends ViewModel {
    private final HistoryRepo historyRepo = HistoryRepo.getInstance();
    public LiveData<ArrayList<HistoryItem>> getHistoryItems() {
        return historyRepo.getHistoryItems();
    }
    public void addHistoryItem(HistoryItem item) {
        historyRepo.addHistoryItem(item);
    }

}
