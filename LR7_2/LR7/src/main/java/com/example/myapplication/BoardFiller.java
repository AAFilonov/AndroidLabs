package com.example.myapplication;

import java.util.ArrayList;
import java.util.Random;

public class BoardFiller {
    Random random = new Random();

    public void fillBoard(Board board, int SIZE) {

        ArrayList<Integer> valuePool = new ArrayList<>();


        for (int i = 1; i <= SIZE * SIZE / 2; i++) {
            valuePool.add(i);
            valuePool.add(i);

        }

        fill(board, valuePool);
    }

    private void fill(Board board, ArrayList<Integer> valuePool) {
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                int value = getValue(board.size,  valuePool);

                board.setCell(i, j,
                        new Cell(value));
            }
        }

    }

    private int getValue(int size, ArrayList<Integer> valuePool) {
        int valueIndex = random.ints(0, size * size / 2 - 1).findFirst().getAsInt();

        while (valuePool.get(valueIndex) == 0) {
            valueIndex++;
        }
        int value = valuePool.get(valueIndex);
        valuePool.set(valueIndex, 0);
        return value;
    }
}
