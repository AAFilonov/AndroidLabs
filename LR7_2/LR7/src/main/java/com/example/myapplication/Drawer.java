package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Drawer {
    Paint paint = new Paint();
    Canvas canvas;
    int displayWidth;
    public static boolean showValues = false;

    public Drawer(Canvas canvas, int width) {
        this.canvas = canvas;
        displayWidth = width;
    }

    public void draw(Board board) {
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                this.draw(board.getCell(i, j),
                        20 + i * displayWidth / board.size,
                        20 + j * displayWidth / board.size,
                        displayWidth / (board.size) - 40);
            }
        }
    }

    public void draw(Cell cell, int x, int y, int cellSize) {
        if (cell.isOpen) paint.setColor(Color.LTGRAY);
        else paint.setColor(Color.GRAY);


        canvas.drawRect(new Rect(x, y, x + cellSize, y + cellSize), paint);
        if (cell.isOpen||showValues) {
            paint.setColor(Color.RED);
            paint.setTextSize(50);
            canvas.drawText(cell.value.toString(), x + cellSize / 2, y + cellSize / 2, paint);
        }

    }

    public void drawBG() {
        canvas.drawColor(Color.WHITE);

    }


    public void drawGrid(int SIZE) {

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        for (int i = 1; i <= SIZE; i++) {
            canvas.drawLine(0, i * (displayWidth / SIZE), displayWidth, i * (displayWidth / SIZE), paint);
            canvas.drawLine(i * displayWidth / SIZE, 0, i * displayWidth / SIZE, displayWidth, paint);
        }
    }

    public void drawStatus(Integer turnLimit, boolean isPlayerWin) {
        String status = new String();
        if (isPlayerWin) status = "Победа!";
        else if (turnLimit == 0) status = "Поражение...";
        else status = "Ходов осталось: " + turnLimit;

        paint.setTextSize(50);
        canvas.drawText(status, 10, displayWidth + 50, paint);
    }
}
