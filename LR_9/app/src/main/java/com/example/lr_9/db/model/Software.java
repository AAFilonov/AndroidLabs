package com.example.lr_9.db.model;

import java.time.LocalDate;

public class Software implements GroupItem {
    Integer id;
    String name;
    String description;
    Long cost;
    String developmentDate;
    String version;

    public Software() {
    }

    public Software(Integer id, String name, String description, Long cost, String developmentDate, String version) {
        this.id = id;
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

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
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

    @Override
    public String getInfo() {
        return getName();
    }
}
