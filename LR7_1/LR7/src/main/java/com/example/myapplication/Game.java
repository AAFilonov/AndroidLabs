package com.example.myapplication;

public class Game {
    private final int PLAYER1_CODE = 1;
    private final int PLAYER2_CODE = 2;
    private int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    private int currentPlayerCode = PLAYER1_CODE;

    public void setCell(int i, int j) {
        board[i][j] = currentPlayerCode;
    }

    public boolean isCellEmpty(int i, int j) {
        return board[i][j] == 0;
    }

    public int getCell(int i, int j) {
        return board[i][j];
    }

    public void switchPlayers() {
        currentPlayerCode = currentPlayerCode == PLAYER2_CODE ? PLAYER1_CODE : PLAYER2_CODE;
    }

    public int getCurrentPlayerCode() {
        return currentPlayerCode;
    }

    public int checkGameState() {
        int state = 0;
        if (board[0][0] != 0 && board[0][0] == board[0][1] && board[0][1] == board[0][2]) state = 1;
        else if (board[1][0] != 0 && board[1][0] == board[1][1] && board[1][1] == board[1][2])
            state = 2;
        else if (board[2][0] != 0 && board[2][0] == board[2][1] && board[2][1] == board[2][2])
            state = 3;
        else if (board[0][0] != 0 && board[0][0] == board[1][0] && board[1][0] == board[2][0])
            state = 4;
        else if (board[0][1] != 0 && board[0][1] == board[1][1] && board[1][1] == board[2][1])
            state = 5;
        else if (board[0][2] != 0 && board[0][2] == board[1][2] && board[1][2] == board[2][2])
            state = 6;
        else if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            state = 7;
        else if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            state = 8;
        if (board[0][0] * board[0][1] * board[0][2]
                * board[1][0] * board[1][1] * board[1][2]
                * board[2][0] * board[2][1] * board[2][2]
                != 0
        ) state = 9;

        return state;
    }
}
