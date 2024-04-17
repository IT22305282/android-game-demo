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
import com.example.andriod_game_demo.helpers.GameConstants;
import com.example.andriod_game_demo.inputs.TouchEvents;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private Paint redPaint = new Paint();
    private SurfaceHolder holder;
    private float x, y;
    private boolean movePlayer;
    private PointF lastTouchDiff;
    private Random rand = new Random();
    private GameLoop gameLoop;
    private TouchEvents touchEvents;
    private PointF skeletonPos;
    private int skeletonDir = GameConstants.Face_Dir.DOWN;
    private long lastDirChange = System.currentTimeMillis();
    private int playerAniIndexY, playerFaceDir = GameConstants.Face_Dir.DOWN;
    private int aniTick;
    private int aniSpeed = 10;

    public GamePanel(Context context){
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);
        touchEvents = new TouchEvents(this);

        gameLoop = new GameLoop(this);

        skeletonPos = new PointF(rand.nextInt(1080), rand.nextInt(2340));
//        for(int i = 0; i < 50; i++){
//            skeletons.add(new PointF(rand.nextInt(1440), rand.nextInt(2960)));
//        }
    }

    public void render(){

        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);

        touchEvents.draw(c);

        c.drawBitmap(GameCharacters.PLAYER.getSprite(playerAniIndexY, playerFaceDir), x, y, null);

//        c.drawBitmap(GameCharacters.SKELETON.getSprite(playerAniIndexY, skeletonDir), skeletonPos.x, skeletonPos.y,null);

//        for(PointF pos: skeletons){
//            c.drawBitmap(GameCharacters.SKELETON.getSprite(0,0),  pos.x, pos.y,null);
//        }

        holder.unlockCanvasAndPost(c);
    }

    public void update(double delta){

        if(System.currentTimeMillis() - lastDirChange >= 3000){
            skeletonDir = rand.nextInt(4);
            lastDirChange = System.currentTimeMillis();
        }

        switch (skeletonDir){
            case GameConstants.Face_Dir.DOWN:
                skeletonPos.y += delta * 300;
                if(skeletonPos.y >= 2140)
                    skeletonDir = GameConstants.Face_Dir.UP;
                break;
            case GameConstants.Face_Dir.UP:
                skeletonPos.y -= delta * 300;
                if(skeletonPos.y <= 0)
                    skeletonDir = GameConstants.Face_Dir.DOWN;
                break;
            case GameConstants.Face_Dir.RIGHT:
                skeletonPos.x += delta * 300;
                if(skeletonPos.x >= 1080)
                    skeletonDir = GameConstants.Face_Dir.LEFT;
                break;
            case GameConstants.Face_Dir.LEFT:
                skeletonPos.x -= delta * 300;
                if(skeletonPos.x <= 0)
                    skeletonDir = GameConstants.Face_Dir.RIGHT;
                break;
        }

        updateplayerMove(delta);

        updateAnimation();
    }

    private void updateplayerMove(double delta){
        if(!movePlayer)
            return;

        float baseSpeed = (float) (delta * 300);
        float ratio = Math.abs(lastTouchDiff.y) / Math.abs(lastTouchDiff.x);
        double angle = Math.atan(ratio);

        float xSpeed = (float) Math.cos(angle);
        float ySpeed = (float) Math.sin(angle);

//        System.out.println("Angle: " + Math.toDegrees(angle));
//        System.out.println("xSpeed: " + xSpeed + " | ySpeed: " + ySpeed);

        if(xSpeed > ySpeed){
            if(lastTouchDiff.x > 0) {
                playerFaceDir = GameConstants.Face_Dir.RIGHT;
            } else {
                playerFaceDir = GameConstants.Face_Dir.LEFT;
            }
        }else{
            if(lastTouchDiff.y >= 0){
                playerFaceDir = GameConstants.Face_Dir.DOWN;
            }else{
                playerFaceDir = GameConstants.Face_Dir.UP;
            }
        }

        if(lastTouchDiff.x < 0)
            xSpeed *= -1;

        if(lastTouchDiff.y < 0)
            ySpeed *= -1;

        x += xSpeed * baseSpeed;
        y += ySpeed * baseSpeed;

    }

    private void updateAnimation(){
        if(!movePlayer){
            return;
        }
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            playerAniIndexY++;
            if(playerAniIndexY >= 4){
                playerAniIndexY = 0;
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return touchEvents.touchEvent(event);
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

    public void setPlayerMoveTrue(PointF lastTouchDiff){
        movePlayer = true;
        this.lastTouchDiff = lastTouchDiff;
    }

    public void setPlayerMoveFalse(){
        movePlayer = false;
        resetAnimation();
    }

    private void resetAnimation() {
        aniTick = 0;
        playerAniIndexY = 0;
    }


}
