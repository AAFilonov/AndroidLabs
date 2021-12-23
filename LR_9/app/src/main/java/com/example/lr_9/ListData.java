package com.example.lr_9;

import androidx.annotation.NonNull;

import com.example.lr_9.db.StaticDatabase;
import com.example.lr_9.db.model.Group;
import com.example.lr_9.db.model.Item;
import com.example.lr_9.db.model.Subgroup;

import java.util.ArrayList;
import java.util.List;

public class ListData {
    public ArrayList<Group> getGroups() {
        return StaticDatabase.getInstance().getGroups();
    }

    static boolean isInited = false;

    public ListData() {
        if (!isInited) {
            StaticDatabase.getInstance().insertOrUpdateGroup(new Group(1, "Десктопное ПО"));
            StaticDatabase.getInstance().insertOrUpdateSubgroup(new Subgroup(1, "Графические редакторы", 1));
            StaticDatabase.getInstance().insertOrUpdateSubgroup(new Subgroup(2, "Среды разработки", 1));
            insertIDES();
            insertGE();
            isInited = true;
        }
    }

    public void insertIDES() {

        List<Item> ides = new ArrayList<>();
        ides.add(
                new Item(1,
                        1,
                        "MS Visual Studio",

                        "",
                        10,
                        "November 23, 2021",
                        "21.1"
                ));
        ides.add(
                new Item(2,
                        1,
                        "Visual Studio Code",

                        "Photoshop but better",
                        20,
                        "December 14, 2021",
                        "1.11.6"
                ));
        ides.add(
                new Item(3,
                        1,
                        "Intellij IDEA",
                        "",
                        20,
                        "September 18, 2021",
                        " 2.10.28"
                ));

        ides.forEach(item -> StaticDatabase.getInstance().insertOrUpdateItem(item));

    }


    public void insertGE() {
        List<Item> graphic_editors = new ArrayList<>();
        graphic_editors.add(
                new Item(4,
                        2,
                        "Adobe Photoshop 2021",
                        "Adobe Photoshop is a raster graphics editor developed and published by Adobe Inc.",
                        100,
                        "November 23, 2021",
                        "21.1"
                ));
        graphic_editors.add(
                new Item(5,
                        2,
                        "Clip Studio Paint",
                        "Photoshop but better",
                        20,
                        "December 14, 2021",
                        "1.11.6"
                ));
        graphic_editors.add(
                new Item(6,
                        2,
                        "Gimp",
                        "GIMP is a cross-platform image editor available for GNU/Linux, macOS, Windows and more operating systems. ",
                        20,
                        "September 18, 2021",
                        " 2.10.28"
                ));
        graphic_editors.forEach(item -> StaticDatabase.getInstance().insertOrUpdateItem(item));

    }
}
