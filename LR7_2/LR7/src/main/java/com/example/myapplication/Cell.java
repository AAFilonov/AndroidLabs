package com.example.myapplication;

public class Cell {

    Integer value;
    boolean isOpen =false;

    Cell(int value){
        this.value = value;
    }
    public  void flip(){
        isOpen=!isOpen;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
