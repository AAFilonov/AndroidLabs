package com.example.lr_9.db.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Item implements Serializable {
    Integer id;
    Integer group_id;
    String name;
    String description;
    Integer cost;
    String developmentDate;
    String version;

    public Item() {
    }

    public Item(Integer id, Integer group_id, String name, String description, Integer cost, String developmentDate, String version) {
        this.id = id;
        this.group_id = group_id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.developmentDate = developmentDate;
        this.version = version;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDevelopmentDate() {
        return developmentDate;
    }

    public void setDevelopmentDate(String developmentDate) {
        this.developmentDate = developmentDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "Software{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", developmentDate='" + developmentDate + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
