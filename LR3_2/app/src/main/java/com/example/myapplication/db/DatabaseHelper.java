package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Order;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "orders.db";
    private static final int SCHEMA = 3;
    static final String TABLE = "orders";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createOrdersTable(db);
    }

    private void createOrdersTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" +
                "_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "orderTypeName" + " TEXT, " +
                "hallName" + " TEXT, " +
                "firstName" + " TEXT, " +
                "SecondName" + " TEXT, " +
                "phone" + " TEXT" +
                ")"
        );
        // добавление начальных данных
        db.execSQL("INSERT INTO " + TABLE + " (" + "orderTypeName, hallName,firstName,SecondName,phone)"
                + " VALUES ('Частное лицо','Костычева 3','Иван' ,'Иванов', '+7234567799');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void insertOrder(Order order) {
        ContentValues content = new ContentValues();
        content.put("orderTypeName", order.getOrderTypeName());
        content.put("hallName", order.getHallName());
        content.put("firstName", order.getFirstName());
        content.put("SecondName", order.getSecondName());
        content.put("phone", order.getPhone());
        getReadableDatabase().insert(TABLE, null, content);
    }

    public  ArrayList <String> getOrders() {
        ArrayList <String> orders = new ArrayList<>();
        int i=0;
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE, null);
        if (cursor.moveToFirst()) {
            do {

                StringBuilder output = new StringBuilder();

                output.append(cursor.getString(0) + " ,");
                output.append(cursor.getString(1) + " ,");
                output.append(cursor.getString(2) + " ,");
                output.append(cursor.getString(3) + " ,");
                output.append(cursor.getString(4) + " ,");
                output.append(cursor.getString(5) + "");
                orders.add( output.toString());

            } while (cursor.moveToNext());
        }
        cursor.close();
        return orders;
    }

    public Cursor getOrdersCursor() {
        return getReadableDatabase().rawQuery("SELECT * FROM " + TABLE, null);
    }
}
