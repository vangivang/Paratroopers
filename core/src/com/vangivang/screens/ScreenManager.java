package com.vangivang.screens;

/**
 * Created by alonm on 4/14/15.
 */
public class ScreenManager {

    private static Screen mCurrentScreen = null;

    public static void setScreen(Screen screen){
        if (mCurrentScreen != null){
            mCurrentScreen.dispose();
        }
        mCurrentScreen = screen;
        mCurrentScreen.create();
    }

    public static Screen getCurrentScreen(){
        return mCurrentScreen;
    }

}
