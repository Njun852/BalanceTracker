package com.example.balancetracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.balancetracker.HistoryItem.HistoryItem;
import com.example.balancetracker.HistoryItem.HistoryItemType;
import com.example.balancetracker.data.repository.HistoryRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HistoryViewModel extends AndroidViewModel {
    private final HistoryRepo historyRepo;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        historyRepo = HistoryRepo.getInstance(application);
    }

    public LiveData<ArrayList<HistoryItem>> getHistoryItems() {
        return historyRepo.getHistoryItems();
    }
    public void addHistoryItem(HistoryItem item) {
        historyRepo.addHistoryItem(item);
    }

}
