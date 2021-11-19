package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    private final int SIZE = 4;
    Board board = new Board(SIZE);
    BoardFiller filler = new BoardFiller();
    Game game = new Game();
    int turnLimit = SIZE*SIZE*2;
    boolean isPlayerWin =false;
    Paint paint = new Paint();
    Context context;

    public DrawView(Context context) {
        super(context);
        this.context = context;
        SoundPlayer.init(context);
        filler.fillBoard(board, SIZE);
    }

    private int width = getResources().getDisplayMetrics().widthPixels;

    @Override
    protected void onDraw(Canvas canvas) {
        Drawer drawer = new Drawer(canvas, width);
        int width = context.getResources().getDisplayMetrics().widthPixels;
        drawer.drawBG();
        drawer.drawGrid(SIZE);
        drawer.draw(board);

        drawer.drawStatus(turnLimit, isPlayerWin);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isPlayerWin||turnLimit==0) return true;
        if (((MainActivity) context).isSoundOn)
            SoundPlayer.playClick();

        return processTurn(event);
    }

    private boolean processTurn(MotionEvent event) {
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {

            float X = event.getX();
            float Y = event.getY();
            int width = getResources().getDisplayMetrics().widthPixels;
            int cellSize = width / SIZE;

            int i = 0, j = 0;
            if (X >= 0 && X <= cellSize)
                i = 0;
            else if (X >= cellSize + 10 && X <= 2 * cellSize + 10)
                i = 1;
            else if (X >= 2 * cellSize + 10 && X <= 3 * cellSize + 10)
                i = 2;
            else if (X >= 3 * (cellSize + 10))
                i = 3;

            if (Y >= 0 && Y <= cellSize)
                j = 0;
            else if (Y >= cellSize + 10 && Y <= 2 * cellSize + 10)
                j = 1;
            else if (Y >= 2 * cellSize + 10 && Y <= 3 * cellSize + 10)
                j = 2;
            else if (Y >= 3 * (cellSize + 10))
                j = 3;




            game.doTurn(new Turn(i,j, board.getCell(i,j)));
            invalidate();
            Runnable runnable = () -> {
                game.doPause();
                game.checkPreviousTurn();
            };
            Thread t = new Thread(runnable);
            t.start();
            isPlayerWin = game.CheckWin(board);
            turnLimit--;
        }
        return true;
    }
    public  void  redraw(){
        invalidate();
    }
}
