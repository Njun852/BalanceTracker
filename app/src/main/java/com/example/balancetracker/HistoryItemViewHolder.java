package com.example.balancetracker;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryItemViewHolder extends RecyclerView.ViewHolder{
    TextView nameTxt, subTxt, priceTxt;

    public HistoryItemViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.nameTxt);
        subTxt = itemView.findViewById(R.id.subTxt);
        priceTxt = itemView.findViewById(R.id.priceTxt);
    }
}
