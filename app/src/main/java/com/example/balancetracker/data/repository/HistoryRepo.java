package com.example.balancetracker.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.balancetracker.HistoryItem.HistoryItem;
import com.example.balancetracker.data.local.DatabaseHelper;

import java.util.ArrayList;

public class HistoryRepo {
    private static HistoryRepo instance;
    private final DatabaseHelper dbHelper;
    private final MutableLiveData<ArrayList<HistoryItem>> historyItems = new MutableLiveData<>();

    private HistoryRepo(Context context) {
        historyItems.setValue(new ArrayList<>());
        dbHelper = new DatabaseHelper(context);
        loadDataFromDB();
    }

    public static synchronized HistoryRepo getInstance(Context context) {
        if(instance == null) {
            instance = new HistoryRepo(context.getApplicationContext());
        }
        return instance;
    }

    public LiveData<ArrayList<HistoryItem>> getHistoryItems() {
        return historyItems;
    }
    public void addHistoryItem(HistoryItem item) {
        new Thread(()->{
            long id = dbHelper.insertHistory(item);
            if(id != -1) {
                loadDataFromDB();
            }
        }).start();
    }

    private void loadDataFromDB() {
        new Thread(()->{
            ArrayList<HistoryItem> items = dbHelper.getAllHistory();
            historyItems.postValue(items);
        }).start();
    }
}
