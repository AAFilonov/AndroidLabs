package com.example.lr_9.db.model;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {
    private Integer id;
    private String name;
    private List<Item> items;

    public Group(){

    }

    public Group(Integer id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
