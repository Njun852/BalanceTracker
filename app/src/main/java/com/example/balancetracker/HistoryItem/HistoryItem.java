package com.example.balancetracker.HistoryItem;

import java.time.LocalDateTime;

public class HistoryItem {
    String name;
    HistoryItemType type;
    int amount;
    LocalDateTime date;

    public HistoryItem(String name, HistoryItemType type, int amount, LocalDateTime date) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HistoryItemType getType() {
        return type;
    }

    public void setType(HistoryItemType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

