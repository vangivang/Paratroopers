package com.vangivang.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.camera.OrthoCamera;
import com.vangivang.entity.EntityManager;
import com.vangivang.entity.Player;

/**
 * Created by alonm on 4/14/15.
 */
public class GameScreen extends Screen {

    private OrthoCamera mCamera;
    private EntityManager mEntityManager;

    @Override
    public void create() {
        mCamera = new OrthoCamera();
        mEntityManager = new EntityManager(10);
    }

    @Override
    public void update() {
        mCamera.update();
        mEntityManager.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        mEntityManager.render(sb);
        sb.end();
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
