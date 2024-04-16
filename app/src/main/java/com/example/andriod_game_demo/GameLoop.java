package com.example.andriod_game_demo;

public class GameLoop implements Runnable{

    private Thread gameThread;
    private GamePanel gamePanel;
    public GameLoop(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        gameThread = new Thread(this);
    }

    @Override
    public void run() {
        long lastFPScheck = System.currentTimeMillis();
        while(true){
            gamePanel.update();
            gamePanel.render();
        }
    }

    public void startGameLoop(){
        gameThread.start();
    }
}
