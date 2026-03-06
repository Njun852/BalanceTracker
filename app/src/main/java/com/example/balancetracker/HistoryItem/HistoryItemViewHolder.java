package com.example.balancetracker.HistoryItem;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.balancetracker.R;

public class HistoryItemViewHolder extends RecyclerView.ViewHolder{
    TextView nameTxt, subTxt, priceTxt;
    CardView card;

    public HistoryItemViewHolder(@NonNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.card);
        nameTxt = itemView.findViewById(R.id.nameTxt);
        subTxt = itemView.findViewById(R.id.subTxt);
        priceTxt = itemView.findViewById(R.id.priceTxt);
    }
}
