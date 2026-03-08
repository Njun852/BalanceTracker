package com.example.balancetracker.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.balancetracker.HistoryItem.HistoryItem;
import com.example.balancetracker.HistoryItem.HistoryItemType;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BalanceTracker.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_HISTORY = "history";
    public static final String HISTORY_ID = "id";
    public static final String HISTORY_NAME = "name";
    public static final String HISTORY_TYPE = "history_type";
    public static final String HISTORY_AMOUNT = "history_amount";
    public static final String HISTORY_DATE = "history_date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String historyTable = "CREATE TABLE "+TABLE_HISTORY+" ("+
                HISTORY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                HISTORY_NAME + " TEXT, "+
                HISTORY_TYPE +" TEXT, "+
                HISTORY_AMOUNT + " INTEGER, "+
                HISTORY_DATE +" INTEGER"+
                ")";

        db.execSQL(historyTable);
    }

    public ArrayList<HistoryItem> getAllHistory() {
        ArrayList<HistoryItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_HISTORY+" ORDER BY "+HISTORY_DATE+ " DESC", null);

        if(cursor.moveToFirst()) {
            do {
                HistoryItem item = new HistoryItem(
                        cursor.getString(1),
                        HistoryItemType.valueOf(cursor.getString(2)),
                        cursor.getInt(3),
                        LocalDateTime.ofEpochSecond(cursor.getLong(4), 0, ZoneOffset.UTC)
                );

                items.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return items;
    }

    public long insertHistory(HistoryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(HISTORY_NAME, item.getName());
        values.put(HISTORY_AMOUNT, item.getAmount());
        values.put(HISTORY_TYPE, item.getType().toString());
        long date = item.getDate().toEpochSecond(ZoneOffset.UTC);
        values.put(HISTORY_DATE, date);

        long id = db.insert(TABLE_HISTORY, null, values);
        db.close();
        return id;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HISTORY);
        onCreate(db);
    }
}
