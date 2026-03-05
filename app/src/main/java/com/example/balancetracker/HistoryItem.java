package com.example.balancetracker;

import java.util.Date;

public class HistoryItem {
    String name;
    String type;
    int amount;
    Date date;

    public HistoryItem(String name, String type, int amount, Date date) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
