package com.example.lr_9.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.lr_9.db.model.Group;
import com.example.lr_9.db.model.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lr9.db";
    private static final int SCHEMA = 0;
    static final String TABLE_GROUPS = "groups";
    static final String TABLE_ITEMS = "items";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createGroupsTable(db);
        createItemsTable(db);
    }

    private void createGroupsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_GROUPS + " (" +
                "group_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name" + " TEXT " +
                ")"
        );
        // добавление начальных данных
        //       db.execSQL("INSERT INTO " + TABLE + " (" + "orderTypeName, hallName,firstName,SecondName,phone)"
        //           + " VALUES ('Частное лицо','Костычева 3','Иван' ,'Иванов', '+7234567799');");
    }


    private void createItemsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_ITEMS + " (" +
                "group_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name" + " TEXT " +
                ")"
        );
        // добавление начальных данных
        //       db.execSQL("INSERT INTO " + TABLE + " (" + "orderTypeName, hallName,firstName,SecondName,phone)"
        //           + " VALUES ('Частное лицо','Костычева 3','Иван' ,'Иванов', '+7234567799');");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public void insertGroup(Group group) {
        ContentValues content = new ContentValues();
        content.put("name", group.getName());
        content.put("group_id", group.getId());
        getWritableDatabase().insert(TABLE_GROUPS, null, content);
    }

    public void insertItem(Item software) {
        ContentValues content = new ContentValues();
        if (software.getId() != null)
            throw new RuntimeException("ID should be null");

        content.put("group_id", software.getGroup_id());
        content.put("cost", software.getCost());
        content.put("description", software.getDescription());
        content.put("version", software.getVersion());
        content.put("devDate", software.getDevelopmentDate());

        getWritableDatabase().insert(TABLE_GROUPS, null, content);
    }

    public void updateItem(Item software) {
        ContentValues content = new ContentValues();
        content.put("item_id", software.getId());
        content.put("group_id", software.getGroup_id());
        content.put("cost", software.getCost());
        content.put("description", software.getDescription());
        content.put("version", software.getVersion());
        content.put("devDate", software.getDevelopmentDate());

        String[] args = new String[]{software.getId().toString()};
        getWritableDatabase().update(TABLE_GROUPS, content, "item_id = ?", args);
    }


    public void updateGroup(Group group) {
        ContentValues content = new ContentValues();
        content.put("name", group.getName());
        content.put("group_id", group.getId());

        String[] args = new String[]{group.getId().toString()};
        getWritableDatabase().update(TABLE_GROUPS, content, "group_id = ?", args);
    }

    public ArrayList<String> getOrders() {
        ArrayList<String> orders = new ArrayList<>();
        int i = 0;
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_GROUPS, null);
        if (cursor.moveToFirst()) {
            do {

                StringBuilder output = new StringBuilder();

                output.append(cursor.getString(0) + " ,");
                output.append(cursor.getString(1) + " ,");
                output.append(cursor.getString(2) + " ,");
                output.append(cursor.getString(3) + " ,");
                output.append(cursor.getString(4) + " ,");
                output.append(cursor.getString(5) + "");
                orders.add(output.toString());

            } while (cursor.moveToNext());
        }
        cursor.close();
        return orders;
    }


}
