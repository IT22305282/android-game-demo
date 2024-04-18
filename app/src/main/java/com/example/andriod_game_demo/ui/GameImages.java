package com.example.andriod_game_demo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.andriod_game_demo.R;
import com.example.andriod_game_demo.helpers.interfaces.BitmapMethods;
import com.example.andriod_game_demo.main.MainActivity;

public enum GameImages implements BitmapMethods {

    MAINMENU_MENUBG(R.drawable.mainmenu_menubackground);

    private final Bitmap image;

    GameImages(int resID) {
        options.inScaled = false;
        image = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources() , resID , options);
    }

    public Bitmap getImage() {
        return image;
    }
}
