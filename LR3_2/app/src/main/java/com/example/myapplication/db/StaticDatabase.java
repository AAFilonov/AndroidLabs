package com.example.myapplication.db;

import android.content.ContextWrapper;

public class StaticDatabase {
    private static DatabaseHelper db;

    private StaticDatabase() {

    }

    public static void init(ContextWrapper context) {
        db = new DatabaseHelper(context);
    }

    public static DatabaseHelper getInstance() {
        return db;
    }
}
