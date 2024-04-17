package com.example.andriod_game_demo.gamestates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.andriod_game_demo.helpers.GameConstants;
import com.example.andriod_game_demo.helpers.interfaces.GameStateInterface;
import com.example.andriod_game_demo.main.Game;
import com.example.andriod_game_demo.main.MainActivity;

public class Menu extends BaseState implements GameStateInterface {

    private Paint paint;
    public Menu(Game game){
        super(game);
        paint = new Paint();
        paint.setTextSize(60);
        paint.setColor(Color.WHITE);
    }


    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Canvas c) {
        c.drawText("MENU", MainActivity.GAME_WIDTH/2, MainActivity.GAME_HEIGHT/2, paint);
    }

    @Override
    public void touchEvents(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
            game.setCurrentGameState(Game.GameState.PLAYING);
    }
}
