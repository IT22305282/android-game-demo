package com.example.andriod_game_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private Paint redPaint = new Paint();
    private SurfaceHolder holder;
//    private float x,y;
    private ArrayList<PointF> squarePos = new ArrayList<>();

    public GamePanel(Context context){
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);
    }

    private void render(){

        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);

        for(PointF pos : squarePos)
            c.drawRect(pos.x,pos.y,pos.x+50,pos.y+50,redPaint);

        holder.unlockCanvasAndPost(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            squarePos.add(new PointF(event.getX(),event.getY()));
            render();
        }

        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        render();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
