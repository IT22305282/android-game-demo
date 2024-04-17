package com.example.andriod_game_demo.gamestates;

import com.example.andriod_game_demo.main.Game;

public abstract class BaseState {
    protected Game game;

    public BaseState(Game game) {
        this.game = game;
    }

    public Game getGame(){
        return game;
    }
}
