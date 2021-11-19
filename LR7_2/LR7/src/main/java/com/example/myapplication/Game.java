package com.example.myapplication;

import java.util.Arrays;
import java.util.Stack;

public class Game {

    private Stack<Turn> turns = new Stack<>();


    public void doTurn(Turn newTurn) {


        newTurn.cell.flip();

        turns.add(newTurn);
    }

    public void checkPreviousTurn() {
        if (turns.size() == 2) {
            Turn thisTurn = turns.pop();
            Turn previousTurn  = turns.pop();
            if (previousTurn.cell.getValue() != thisTurn.cell.getValue()) {
                thisTurn.cell.flip();
                previousTurn.cell.flip();
            }
        }
    }

    public void doPause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  boolean CheckWin(Board board){
        return Arrays.stream(board.board).allMatch(cells -> Arrays.stream(cells).allMatch(cell -> cell.isOpen));

    }
}
