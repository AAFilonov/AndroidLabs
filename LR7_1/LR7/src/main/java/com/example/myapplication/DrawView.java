package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {


    Paint paint = new Paint();
    static Game game = new Game();
    Context context;

    public DrawView(Context context) {
        super(context);
        this.context = context;
        SoundPlayer.init(context);

    }

    private int width = getResources().getDisplayMetrics().widthPixels;

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        width = getResources().getDisplayMetrics().widthPixels;

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        drawGrid(canvas, width);
        drawCells(canvas, width);
        drawStatus(canvas, width);
        drawWinStripe(canvas);
    }

    private void drawWinStripe(Canvas canvas) {
        if (game.checkGameState() == 0 || game.checkGameState() == 9) return;
        int color = game.getCurrentPlayerCode() == 1 ? Color.RED : Color.BLUE;
        paint.setColor(color);
        canvas.drawRect(new Rect(0, width / 4, width, 3 * width / 4), paint);
        String text = game.getCurrentPlayerCode() == 1 ? "Победа первого игрока" : "Победа второго игрока";
        paint.setColor(Color.WHITE);
        canvas.drawText(text, 300, width / 2, paint);
    }

    private void drawStatus(Canvas canvas, int width) {
        String s = new String();

        int stat = game.checkGameState();

        if (stat == 0 && game.getCurrentPlayerCode() == 1) s = "Ход первого игрока";
        if (stat == 0 && game.getCurrentPlayerCode() == 2) s = "Ход второго игрока";

        if (stat != 0 && stat != 9 && game.getCurrentPlayerCode() == 2) s = "Победа первого игрока";
        if (stat != 0 && stat != 9 && game.getCurrentPlayerCode() == 1) s = "Победа второго игрока";
        if (stat == 9) s = "Ничья";

        paint.setTextSize(50);
        canvas.drawText(s, 10, width + 50, paint);

    }

    private void drawCells(Canvas canvas, int width) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (game.getCell(i, j) == 1)
                    drawPlayerPic(1, canvas, 50 + i * width / 3, 50 + j * width / 3);
                else if (game.getCell(i, j) == 2)
                    drawPlayerPic(2, canvas, 50 + i * width / 3, 50 + j * width / 3);
    }

    private void drawGrid(Canvas canvas, int width) {
        canvas.drawLine(0, width / 3, width, width / 3, paint);
        canvas.drawLine(0, 2 * width / 3, width, 2 * width / 3, paint);
        canvas.drawLine(width / 3, 0, width / 3, width, paint);
        canvas.drawLine(2 * width / 3, 0, 2 * width / 3, width, paint);
    }

    private void drawPlayerPic(int playerCode, Canvas canvas, int x, int y) {
        Bitmap bitmap;
        if (playerCode == 1)
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.player1);
        else
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.player2);
        bitmap = Bitmap.createScaledBitmap(bitmap, width / 3 - 100, width / 3 - 100, false);
        canvas.drawBitmap(bitmap, x, y, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (game.checkGameState() != 0) {
            return true;
        }
        if(((MainActivity)context).isSoundOn)
            SoundPlayer.playClick();

        return processTurn(event);
    }

    private boolean processTurn(MotionEvent event) {
        float X = event.getX();
        float Y = event.getY();
        int width = getResources().getDisplayMetrics().widthPixels;
        int cellSize = width / 3;

        int i = 0, j = 0;
        if (X >= 0 && X <= cellSize) i = 0;
        if (X >= cellSize + 10 && X <= 2 * cellSize + 10) i = 1;
        if (X >= 2 * (cellSize + 10)) i = 2;

        if (Y >= 0 && Y <= cellSize) j = 0;
        if (Y >= cellSize + 10 && Y <= 2 * cellSize + 10) j = 1;
        if (Y >= 2 * (cellSize + 10)) j = 2;

        if (game.isCellEmpty(i, j)) {
            game.setCell(i, j);
            game.switchPlayers();
            invalidate();
        }
        return true;
    }
}
