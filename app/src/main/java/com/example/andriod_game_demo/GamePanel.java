package com.example.andriod_game_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.andriod_game_demo.entities.GameCharacters;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private Paint redPaint = new Paint();
    private SurfaceHolder holder;
    private float x, y;
    private Random rand = new Random();
    private GameLoop gameLoop;
    private ArrayList<PointF> skeletons = new ArrayList<>();

    public GamePanel(Context context){
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);

        gameLoop = new GameLoop(this);

        for(int i = 0; i < 50; i++){
            skeletons.add(new PointF(rand.nextInt(1440), rand.nextInt(2960)));
        }
    }

    public void render(){

        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);

//        c.drawBitmap(GameCharacters.PLAYER.getSpriteSheet(), 500, 500, null);

        c.drawBitmap(GameCharacters.PLAYER.getSprite(6,3), x, y, null);

        for(PointF pos: skeletons){
            c.drawBitmap(GameCharacters.SKELETON.getSprite(0,0),  pos.x, pos.y,null);
        }

        holder.unlockCanvasAndPost(c);
    }

    public void update(double delta){
        for(PointF pos: skeletons){
            pos.y += delta * 300;

            if(pos.y >= 2960){
                pos.y = 0;
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            x = event.getX();
            y = event.getY();
        }

        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startGameLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }


}
