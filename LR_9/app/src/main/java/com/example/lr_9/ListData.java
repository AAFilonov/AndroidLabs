package com.example.lr_9;

import androidx.annotation.NonNull;

import com.example.lr_9.db.model.Group;
import com.example.lr_9.db.model.GroupItem;
import com.example.lr_9.db.model.Software;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListData {
    public ArrayList<Group> getGroups() {
        return groupsTitles;
    }

    private ArrayList<Group> groupsTitles;

    public ListData() {
        groupsTitles = new ArrayList<>();

        groupsTitles.add(new Group(1, "Графические редакторы", getGE()));
        groupsTitles.add(new Group(2, "Среды разработки", getIDES()));
        //    groupsTitles.add(new Group(3, "Текстовые редакторы", null));
    }

    public List<Software> getIDES() {

        List<Software> ides = new ArrayList<>();
        ides.add(
                new Software(null, "MS Visual Studio",
                        "",
                        100L,
                        "November 23, 2021",
                        "21.1"
                ));
        ides.add(
                new Software(null, "Visual Studio Code",
                        "Photoshop but better",
                        20L,
                        "December 14, 2021",
                        "1.11.6"
                ));
        ides.add(
                new Software(null, "Intellij IDEA",
                        "",
                        20L,
                        "September 18, 2021",
                        " 2.10.28"
                ));


        return ides;
    }

    @NonNull
    private List<Software> getGE() {
        List<Software> graphic_editors = new ArrayList<>();
        graphic_editors.add(
                new Software(null, "Adobe Photoshop 2021",
                        "Adobe Photoshop is a raster graphics editor developed and published by Adobe Inc.",
                        100L,
                        "November 23, 2021",
                        "21.1"
                ));
        graphic_editors.add(
                new Software(null, "Clip Studio Paint",
                        "Photoshop but better",
                        20L,
                        "December 14, 2021",
                        "1.11.6"
                ));
        graphic_editors.add(
                new Software(null, "Gimp",
                        "GIMP is a cross-platform image editor available for GNU/Linux, macOS, Windows and more operating systems. ",
                        20L,
                        "September 18, 2021",
                        " 2.10.28"
                ));
        return graphic_editors;
    }
}
