package com.example.lr_9.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lr_9.db.model.Group;

import java.util.ArrayList;

public class GroupHelper {
    private DatabaseHelper databaseHelper;
    static final String TABLE_GROUPS = "groups";

    GroupHelper(DatabaseHelper db) {
        this.databaseHelper = db;
    }

    public void createGroupsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_GROUPS + " (" +
                "group_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name" + " TEXT " +
                ")"
        );

    }
    public void insertGroup(Group group) {
        ContentValues content = new ContentValues();
        content.put("name", group.getName());
        content.put("group_id", group.getId());
        databaseHelper.getWritableDatabase().insert(TABLE_GROUPS, null, content);
    }

    public void updateGroup(Group group) {
        ContentValues content = new ContentValues();
        content.put("name", group.getName());
        content.put("group_id", group.getId());

        String[] args = new String[]{group.getId().toString()};
        databaseHelper.getWritableDatabase().update(TABLE_GROUPS, content, "group_id = ?", args);
    }
    public void insertOrUpdateGroup(Group group) {

        if (getGroup(group.getId()) != null)
            updateGroup(group);
        else
            insertGroup(group);
    }
    public Group getGroup(Integer id) {
        Group group = null;
        String[] args = new String[]{id.toString()};

        Cursor cursor =   databaseHelper.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_GROUPS + " WHERE group_id = ?", args);
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                group = new Group();
                group.setId(cursor.getInt(0));
                group.setName(cursor.getString(1));


            }

        }
        return group;
    }
    public ArrayList<Group> getGroupsWithoutLists() {
        ArrayList<Group> groups = new ArrayList<>();
        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_GROUPS, null);
        if (cursor.moveToFirst()) {
            do {
                Group group = new Group();
                group.setId(cursor.getInt(0));
                group.setName(cursor.getString(1));
                group.setName(cursor.getString(1));
                groups.add(group);
            } while (cursor.moveToNext());
        }
        return groups;
    }

    public void deleteGroup(Integer selectedGroupId) {
        String[] args = new String[]{selectedGroupId.toString()};
        int i = databaseHelper.getWritableDatabase().delete(TABLE_GROUPS, "group_id = ?", args);
        System.out.println("Deleted " + i + " group "+selectedGroupId);
    }
}
