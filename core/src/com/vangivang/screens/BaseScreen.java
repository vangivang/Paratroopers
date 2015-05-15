package com.vangivang.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vangivang.camera.OrthoCamera;

/**
 * Created by alonm on 4/14/15.
 */
public abstract class BaseScreen {

    protected OrthoCamera mCamera;

    public BaseScreen(){
        mCamera = new OrthoCamera();
    }

    public abstract void create();
    public abstract void update();
    public abstract void render(SpriteBatch sb);
    public abstract void resize(int width, int height);
    public abstract void dispose();
    public abstract void pause();
    public abstract void resume();
}
