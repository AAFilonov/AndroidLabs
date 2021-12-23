package com.example.lr_9.db.model;

import java.io.Serializable;
import java.util.List;

public class Subgroup implements Serializable {
    private Integer id;
    private Integer group_id;
    private String name;
    private List<Item> items;

    public Subgroup(){

    }

    public Subgroup(Integer id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Subgroup(Integer id, String name,Integer group_id) {
        this.id = id;
        this.name = name;
        this.group_id = group_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
