package com.vangivang.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vangivang.game.MainGame;

/**
 * Created by alonm on 4/14/15.
 */
public abstract class BaseScreen {

    protected OrthographicCamera mCamera;
    protected Viewport mViewPort;

    public BaseScreen(){
        mCamera = new OrthographicCamera();
        mCamera.position.set(MainGame.WIDTH / 2f, MainGame.HEIGHT / 2f, 0);
        mViewPort = new FitViewport(MainGame.WIDTH, MainGame.HEIGHT, mCamera);
        mViewPort.apply();
    }

    public void update(){

    }

    public void resize(int width, int height){
        mViewPort.update(width, height);
    }

    public abstract void create();
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    public abstract void pause();
    public abstract void resume();
}
