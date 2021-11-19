package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Board {
    Cell[][] board ;
    int size;

    public int getSize() {
        return size;
    }

    public Board(int size) {
        board = new Cell[size][size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell(0);
            }
        }
    }

    public  Cell  getCell(int i, int j){
        return  board[i][j];
    }
    public  void  setCell(int i,int j, Cell cell){
        board[i][j] = cell;
    }
    public  void  flipCell(int i, int j){
        board[i][j].flip();
    }

}
