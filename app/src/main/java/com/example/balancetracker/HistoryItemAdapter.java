package com.example.balancetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemViewHolder>{

    Context context;
    ArrayList<HistoryItem> items;

    public HistoryItemAdapter(Context context, ArrayList<HistoryItem> items) {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.history_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {
        HistoryItem item = items.get(position);
        holder.nameTxt.setText(item.name);
        holder.subTxt.setText(item.type+" - "+item.date.toString());
        holder.priceTxt.setText("₱"+item.amount);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
