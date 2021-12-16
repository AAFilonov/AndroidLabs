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
    private static final int SCHEMA = 5;
    static final String TABLE_GROUPS = "groups";
    static final String TABLE_ITEMS = "items";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        createGroupsTable(db);
        createItemsTable(db);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
      // createGroupsTable(db);
      // createItemsTable(db);
    }

    private void createGroupsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_GROUPS + " (" +
                "group_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name" + " TEXT " +
                ")"
        );

    }


    private void createItemsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_ITEMS + " (" +
                "item_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "group_id  INTEGER," +
                "name" + " TEXT, " +
                "cost" + "  INTEGER," +
                "description" + " TEXT ," +
                "version" + " TEXT ," +
                "devDate" + " TEXT " +
                ")"
        );
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

        content.put("item_id", software.getId());
        content.put("group_id", software.getGroup_id());
        content.put("name", software.getName());
        content.put("cost", software.getCost());
        content.put("description", software.getDescription());
        content.put("version", software.getVersion());
        content.put("devDate", software.getDevelopmentDate());

        getWritableDatabase().insert(TABLE_ITEMS, null, content);
    }

    public void updateItem(Item software) {
        ContentValues content = new ContentValues();
        content.put("item_id", software.getId());
        content.put("group_id", software.getGroup_id());
        content.put("name", software.getName());
        content.put("cost", software.getCost());
        content.put("description", software.getDescription());
        content.put("version", software.getVersion());
        content.put("devDate", software.getDevelopmentDate());

        String[] args = new String[]{software.getId().toString()};
        getWritableDatabase().update(TABLE_ITEMS, content, "item_id = ?", args);
    }


    public void insertOrUpdateGroup(Group group) {

        if (getGroup(group.getId()) != null)
            updateGroup(group);
        else
            insertGroup(group);
    }

    public void insertOrUpdateItem(Item item) {

        if (getItem(item.getId()) != null)
            updateItem(item);
        else
            insertItem(item);
    }

    public Item getItem(Integer id) {
        Item item = null;
        String[] args = new String[]{id.toString()};

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_ITEMS + " WHERE item_id = ?", args);
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                item = new Item();
                item.setId(cursor.getInt(0));
                item.setGroup_id(cursor.getInt(1));
                item.setName(cursor.getString(2));
                item.setCost(cursor.getInt(3));
                item.setDescription(cursor.getString(4));
                item.setVersion(cursor.getString(5));
                item.setDevelopmentDate(cursor.getString(6));

            }

        }
        return item;
    }

    public void updateGroup(Group group) {
        ContentValues content = new ContentValues();
        content.put("name", group.getName());
        content.put("group_id", group.getId());

        String[] args = new String[]{group.getId().toString()};
        getWritableDatabase().update(TABLE_GROUPS, content, "group_id = ?", args);
    }

    public Group getGroup(Integer id) {
        Group group = null;
        String[] args = new String[]{id.toString()};

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_GROUPS + " WHERE group_id = ?", args);
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                group = new Group();
                group.setId(cursor.getInt(0));
                group.setName(cursor.getString(1));


            }

        }
        return group;
    }


    public ArrayList<Group> getGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_GROUPS, null);
        if (cursor.moveToFirst()) {
            do {
                Group group = new Group();
                group.setId(cursor.getInt(0));
                group.setName(cursor.getString(1));
                group.setItems(getItemsByGroup(group));
                groups.add(group);
            } while (cursor.moveToNext());
        }
        return groups;
    }

    public ArrayList<Group> getGroupsWithoutLists() {
        ArrayList<Group> groups = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_GROUPS, null);
        if (cursor.moveToFirst()) {
            do {
                Group group = new Group();
                group.setId(cursor.getInt(0));
                group.setName(cursor.getString(1));
                groups.add(group);
            } while (cursor.moveToNext());
        }
        return groups;
    }

    public ArrayList<Item> getItemsByGroup(Group group) {
        ArrayList<Item> items = new ArrayList<>();
        String[] args = new String[]{group.getId().toString()};
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_ITEMS + " WHERE group_id = ?", args);
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(cursor.getInt(0));
                item.setGroup_id(cursor.getInt(1));
                item.setName(cursor.getString(2));
                item.setCost(cursor.getInt(3));
                item.setDescription(cursor.getString(4));
                item.setVersion(cursor.getString(5));
                item.setDevelopmentDate(cursor.getString(6));
                items.add(item);
            } while (cursor.moveToNext());
        }
        return items;
    }

    public void deleteItem(Integer selectedItemId) {
        Item item = null;
        String[] args = new String[]{selectedItemId.toString()};
        int aa = getWritableDatabase().delete(TABLE_ITEMS ,"item_id = ?", args);
        System.out.println("Deleted "+aa+" items");
    }
}


