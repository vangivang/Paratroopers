package com.vangivang.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vangivang.entity.EntityManager;
import com.vangivang.game.MainGame;
import com.vangivang.game.TextureManager;


/**
 * Created by alonm on 4/14/15.
 */
public class GameScreen extends Screen {

    private OrthographicCamera mCamera;
    private Viewport mViewPort;
    private EntityManager mEntityManager;
    private Texture mBackground = TextureManager.BACKGROUND;

    @Override
    public void create() {
        mCamera = new OrthographicCamera(MainGame.WIDTH, MainGame.HEIGHT);
        mCamera.translate(MainGame.WIDTH / 2, MainGame.HEIGHT / 2);
        mCamera.update();
        mEntityManager = EntityManager.getInstance();
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
        sb.draw(mBackground, 0, 0);
        mEntityManager.render(sb);
        sb.end();
    }

    @Override
    public void resize(int width, int height) {
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
