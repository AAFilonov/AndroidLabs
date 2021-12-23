package com.example.lr_9.db.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.lr_9.db.model.Group;
import com.example.lr_9.db.model.Item;
import com.example.lr_9.db.model.Subgroup;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lr9.db";
    private static final int SCHEMA = 8;

    static final String TABLE_SUBGROUPS = "subgroups";
    static final String TABLE_ITEMS = "items";
    GroupHelper groupHelper;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + GroupHelper.TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBGROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        groupHelper = new GroupHelper(this);
        groupHelper.createGroupsTable(db);
        createItemsTable(db);
        createSubgroupsTable(db);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // createGroupsTable(db);
        // createItemsTable(db);
    }


    public void createSubgroupsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_SUBGROUPS + " (" +
                "subgroup_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name" + " TEXT ," +
                "group_id " + "  INTEGER" +
                ")"
        );

    }


    public void createItemsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_ITEMS + " (" +
                "item_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "subgroup_id  INTEGER," +
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
        db.execSQL("DROP TABLE IF EXISTS " + GroupHelper.TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBGROUPS);
        onCreate(db);
    }


    public void insertSubgroup(Subgroup group) {
        ContentValues content = new ContentValues();
        content.put("name", group.getName());
        content.put("subgroup_id", group.getId());
        content.put("group_id", group.getGroup_id());
        getWritableDatabase().insert(TABLE_SUBGROUPS, null, content);
    }

    public void insertItem(Item software) {
        ContentValues content = new ContentValues();

        content.put("item_id", software.getId());
        content.put("subgroup_id", software.getSubgroup_id());
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
        content.put("subgroup_id", software.getSubgroup_id());
        content.put("name", software.getName());
        content.put("cost", software.getCost());
        content.put("description", software.getDescription());
        content.put("version", software.getVersion());
        content.put("devDate", software.getDevelopmentDate());

        String[] args = new String[]{software.getId().toString()};
        getWritableDatabase().update(TABLE_ITEMS, content, "item_id = ?", args);
    }

    public void updateSubgroup(Subgroup subgroup) {
        ContentValues content = new ContentValues();
        content.put("name", subgroup.getName());
        content.put("subgroup_id", subgroup.getId());
        content.put("group_id", subgroup.getGroup_id());

        String[] args = new String[]{subgroup.getId().toString()};
        getWritableDatabase().update(TABLE_SUBGROUPS, content, "subgroup_id = ?", args);
    }


    public void insertOrUpdateSubgroup(Subgroup subgroup) {

        if (getSubgroup(subgroup.getId()) != null)
            updateSubgroup(subgroup);
        else
            insertSubgroup(subgroup);
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
                item.setSubgroup_id(cursor.getInt(1));
                item.setName(cursor.getString(2));
                item.setCost(cursor.getInt(3));
                item.setDescription(cursor.getString(4));
                item.setVersion(cursor.getString(5));
                item.setDevelopmentDate(cursor.getString(6));

            }

        }
        return item;
    }


    public Subgroup getSubgroup(Integer id) {
        Subgroup subgroup = null;
        String[] args = new String[]{id.toString()};

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_SUBGROUPS + " WHERE subgroup_id = ?", args);
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                subgroup = new Subgroup();
                subgroup.setId(cursor.getInt(0));
                subgroup.setName(cursor.getString(1));
                subgroup.setGroup_id(cursor.getInt(2));
                subgroup.setItems(getItemsBySubGroup(subgroup.getId()));
            }

        }
        return subgroup;
    }


    public ArrayList<Group> getGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + GroupHelper.TABLE_GROUPS, null);
        if (cursor.moveToFirst()) {
            do {
                Group group = new Group();
                group.setId(cursor.getInt(0));
                group.setName(cursor.getString(1));
                group.setName(cursor.getString(1));
                group.setItems(getSubgroupsByGroupId(group.getId()));
                groups.add(group);
            } while (cursor.moveToNext());
        }
        return groups;
    }


    public ArrayList<Subgroup> getSubgroupsByGroupId(Integer groupId) {
        ArrayList<Subgroup> subgroups = new ArrayList<>();
        String[] args = new String[]{groupId.toString()};
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_SUBGROUPS + " WHERE group_id = ?", args);
        if (cursor.moveToFirst()) {
            do {
                Subgroup subgroup = new Subgroup();
                subgroup.setId(cursor.getInt(0));
                subgroup.setName(cursor.getString(1));
                subgroup.setGroup_id(cursor.getInt(2));
                subgroup.setItems(getItemsBySubGroup(subgroup.getId()));
                subgroups.add(subgroup);
            } while (cursor.moveToNext());
        }
        return subgroups;
    }

    public ArrayList<Item> getItemsBySubGroup(Integer subgroupId) {
        ArrayList<Item> items = new ArrayList<>();
        String[] args = new String[]{subgroupId.toString()};
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_ITEMS + " WHERE subgroup_id = ?", args);
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(cursor.getInt(0));
                item.setSubgroup_id(cursor.getInt(1));
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

        String[] args = new String[]{selectedItemId.toString()};
        int i = getWritableDatabase().delete(TABLE_ITEMS, "item_id = ?", args);
        System.out.println("Deleted " + i + " item " + selectedItemId);
    }

    public void deleteSubGroup(Integer selectedSubgroupId) {

        String[] args = new String[]{selectedSubgroupId.toString()};
        int i = getWritableDatabase().delete(TABLE_SUBGROUPS, "subgroup_id = ?", args);
        System.out.println("Deleted " + i + " subgroup");
    }


    public void deleteItemsBySubgroup(Integer subgroupId) {

        String[] args = new String[]{subgroupId.toString()};
        int i = getWritableDatabase().delete(TABLE_ITEMS, "subgroup_id = ?", args);
        System.out.println("Deleted " + i + " item ");
    }


    public void deleteSubGroupCascade(Integer selectedSubgroupId) {
        deleteItemsBySubgroup(selectedSubgroupId);
        deleteSubGroup(selectedSubgroupId);
    }


    public void deleteGroupCascade(Integer selectedGroupId) {
        ArrayList<Subgroup> subgroups = getSubgroupsByGroupId(selectedGroupId);
        subgroups.forEach(subgroup -> this.deleteSubGroupCascade(subgroup.getId()));
        groupHelper.deleteGroup(selectedGroupId);

    }


    public void insertGroup(Group group) {
        groupHelper.insertGroup(group);
    }

    public void updateGroup(Group group) {
        groupHelper.updateGroup(group);
    }

    public void insertOrUpdateGroup(Group group) {
        groupHelper.insertOrUpdateGroup(group);
    }

    public Group getGroup(Integer id) {
        return groupHelper.getGroup(id);
    }

    public ArrayList<Group> getGroupsWithoutLists() {
        return groupHelper.getGroupsWithoutLists();
    }

}


