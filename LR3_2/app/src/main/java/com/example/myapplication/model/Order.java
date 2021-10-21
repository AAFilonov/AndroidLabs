package com.example.myapplication.model;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderTypeName;
    private String hallName;
    private String firstName;
    private String SecondName;
    private String phone;

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderTypeName='" + orderTypeName + '\'' +
                ", hallName='" + hallName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", SecondName='" + SecondName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
