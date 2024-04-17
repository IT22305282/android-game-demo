package com.example.andriod_game_demo.environments;

import android.graphics.Canvas;

import com.example.andriod_game_demo.helpers.GameConstants;

public class GameMap {

    private int[][] spriteIds;

    public GameMap(int[][] spriteIds){
        this.spriteIds = spriteIds;
    }

    public int getSpriteID(int xIndex, int yIndex){
        return spriteIds[yIndex][xIndex];
    }

    public int getArrayWidth(){
        return spriteIds[0].length;
    }

    public int getArrayHeight(){
        return spriteIds.length;
    }

}