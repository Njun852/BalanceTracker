package com.example.balancetracker.HistoryItem;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.balancetracker.R;

import java.time.format.DateTimeFormatter;
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
        //"#FD6161" -expense
        HistoryItem item = items.get(position);
        holder.card.setCardBackgroundColor(Color.parseColor(item.getType() == HistoryItemType.INCOME ? "#61FD83" : "#FD6161"));
        holder.nameTxt.setText(item.name);
        char[] type = item.type.toString().toLowerCase().toCharArray();
        type[0] = (type[0]+"").toUpperCase().charAt(0);

        holder.subTxt.setText(new String(type)+" - "+item.date.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
        holder.priceTxt.setText(String.format("₱%,d", item.amount));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
