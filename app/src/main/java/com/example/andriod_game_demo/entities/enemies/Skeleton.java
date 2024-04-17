package com.example.andriod_game_demo.entities.enemies;

import static com.example.andriod_game_demo.MainActivity.GAME_HEIGHT;
import static com.example.andriod_game_demo.MainActivity.GAME_WIDTH;

import android.graphics.PointF;

import com.example.andriod_game_demo.entities.Character;
import com.example.andriod_game_demo.entities.GameCharacters;
import com.example.andriod_game_demo.helpers.GameConstants;

import java.util.Random;

public class Skeleton extends Character {
    private long lastDirChange = System.currentTimeMillis();
    private Random rand = new Random();
    public Skeleton(PointF pos) {
        super(pos, GameCharacters.SKELETON);
    }
    
    public void update(double delta){
        updateMove(delta);
        updateAnimation();
    }

    private void updateMove(double delta) {
        if(System.currentTimeMillis() - lastDirChange >= 3000){
            faceDir = rand.nextInt(4);
            lastDirChange = System.currentTimeMillis();
        }

        switch (faceDir){
            case GameConstants.Face_Dir.DOWN:
                hitbox.top += delta * 300;
                if(hitbox.top >= GAME_HEIGHT)
                    faceDir = GameConstants.Face_Dir.UP;
                break;
            case GameConstants.Face_Dir.UP:
                hitbox.top -= delta * 300;
                if(hitbox.top <= 0)
                    faceDir = GameConstants.Face_Dir.DOWN;
                break;
            case GameConstants.Face_Dir.RIGHT:
                hitbox.left += delta * 300;
                if(hitbox.left >= GAME_WIDTH)
                    faceDir = GameConstants.Face_Dir.LEFT;
                break;
            case GameConstants.Face_Dir.LEFT:
                hitbox.left -= delta * 300;
                if(hitbox.left <= 0)
                    faceDir = GameConstants.Face_Dir.RIGHT;
                break;
        }
    }
}
