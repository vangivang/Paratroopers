package com.vangivang.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.entity.EntityManager;
import com.vangivang.game.TextureManager;


/**
 * Created by alonm on 4/14/15.
 */
public class GameScreen extends BaseScreen {

    private ShapeRenderer mShapeRender;

    @Override
    public void create() {
        mShapeRender = new ShapeRenderer();
    }

    public void update() {
        EntityManager.getInstance().update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(mCamera.combined);
        sb.begin();
        sb.draw(TextureManager.getInstance().getTextureByName(TextureManager.BACKGROUND), 0, 0);
        EntityManager.getInstance().render(sb);
        sb.end();
        Vector2 point = EntityManager.getInstance().getPlayerForDebug().getCannonPointOfInterestForDebug();
        drawCircleAtPoint(point.x, point.y);

    }

    public void drawCircleAtPoint(float x, float y){
        mShapeRender.begin(ShapeRenderer.ShapeType.Filled);
        mShapeRender.setColor(1, 1, 0, 1);
        mShapeRender.circle(x, y, 10);
        mShapeRender.end();
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
