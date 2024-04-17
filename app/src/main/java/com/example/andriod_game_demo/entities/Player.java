package com.example.andriod_game_demo.entities;

import static com.example.andriod_game_demo.MainActivity.GAME_HEIGHT;
import static com.example.andriod_game_demo.MainActivity.GAME_WIDTH;

import android.graphics.PointF;


public class Player extends Character{
    public Player() {
        super(new PointF(GAME_WIDTH/2, GAME_HEIGHT/2), GameCharacters.PLAYER);
    }

    public void update(double delta, boolean movePlayer){
        if(movePlayer){
            updateAnimation();
        }
    }
}
