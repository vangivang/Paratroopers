package com.vangivang.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vangivang.camera.OrthoCamera;

/**
 * Created by alonm on 4/14/15.
 */
public class MenuScreen extends Screen {

    private OrthoCamera mCamera;

    @Override
    public void create() {
        mCamera = new OrthoCamera();
    }

    @Override
    public void update() {
        mCamera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void resize(int width, int height) {
        // Keep aspect ratio the same for all devices
        mCamera.resize();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
