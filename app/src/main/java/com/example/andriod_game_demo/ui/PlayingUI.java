package com.example.andriod_game_demo.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.andriod_game_demo.gamestates.Playing;
import com.example.andriod_game_demo.main.Game;

public class PlayingUI {

    //FOR UI
    private float xCenter = 350, yCenter = 800, radius = 120;
    private Paint cireclePaint;
    private float xTouch, yTouch;
    private boolean touchDown;

    private CustomButton btnMenu;

    private final Playing playing;

    public PlayingUI(Playing playing) {
        this.playing = playing;

        cireclePaint = new Paint();
        cireclePaint.setColor(Color.RED);
        cireclePaint.setStrokeWidth(5);
        cireclePaint.setStyle(Paint.Style.STROKE);

        btnMenu = new CustomButton(40,40, ButtonImages.PLAYING_MENU.getWidth(), ButtonImages.PLAYING_MENU.getHeight());
    }

    public void draw(Canvas c){
        drawUI(c);
    }

    private void drawUI(Canvas c) {
        c.drawCircle(xCenter, yCenter, radius, cireclePaint);

        c.drawBitmap(
                ButtonImages.PLAYING_MENU.getBtnImg(btnMenu.isPushed()),
                btnMenu.getHitbox().left,
                btnMenu.getHitbox().top,
                null
        );
    }

    public void touchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                float a = Math.abs(x - xCenter);
                float b = Math.abs(y - yCenter);
                float c = (float) Math.hypot(a,b);

                if(c <= radius){
                    touchDown = true;
                    xTouch = x;
                    yTouch = y;
                }else{

                    if(isIn(event,btnMenu))
                        btnMenu.setPushed(true);

                }

                break;
            case MotionEvent.ACTION_MOVE:
                if(touchDown){
                    xTouch = event.getX();
                    yTouch = event.getY();

                    float xDiff = xTouch - xCenter;
                    float yDiff = yTouch - yCenter;

                    playing.setPlayerMoveTrue(new PointF(xDiff,yDiff));
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isIn(event,btnMenu))
                    if(btnMenu.isPushed())
                        playing.setGameStateToMenu();

                btnMenu.setPushed(false);
                touchDown = false;
                playing.setPlayerMoveFalse();
                break;
        }
    }

    private boolean isIn(MotionEvent e, CustomButton b){
        return b.getHitbox().contains(e.getX(), e.getY());
    }
}
