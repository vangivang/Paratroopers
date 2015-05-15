package com.vangivang.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vangivang.entity.EntityManager;
import com.vangivang.game.TextureManager;


/**
 * Created by alonm on 4/14/15.
 */
public class GameScreen extends BaseScreen {

    @Override
    public void create() {
    }

    @Override
    public void update() {
        mCamera.update();
        EntityManager.getInstance().update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        sb.draw(TextureManager.getInstance().getTextureByName(TextureManager.BACKGROUND), 0, 0);
        EntityManager.getInstance().render(sb);
        sb.end();
    }

    @Override
    public void resize(int width, int height) {
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
