package com.example.lr_9.db;

import android.content.ContextWrapper;

public class StaticDatabase {
    private static DatabaseHelper db;
    private static boolean isInited = false;

    private StaticDatabase() {

    }

    public static void init(ContextWrapper context) {
        if (!isInited) {
            db = new DatabaseHelper(context);
            isInited = true;
        }
    }

    public static DatabaseHelper getInstance() {
        return db;
    }
}
