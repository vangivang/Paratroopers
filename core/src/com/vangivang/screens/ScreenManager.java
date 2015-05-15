package com.vangivang.screens;

/**
 * Created by alonm on 4/14/15.
 */
public class ScreenManager {

    private static BaseScreen mCurrentScreen = null;

    public static void setScreen(BaseScreen screen){
        if (mCurrentScreen != null){
            mCurrentScreen.dispose();
        }
        mCurrentScreen = screen;
        mCurrentScreen.create();
    }

    public static BaseScreen getCurrentScreen(){
        return mCurrentScreen;
    }

}
