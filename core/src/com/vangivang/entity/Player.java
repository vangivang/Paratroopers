package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.camera.OrthoCamera;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/14/15.
 */
public class Player {

    private long mLastFired = 0;
    private Vector2 mBasePosition;
    private Vector2 mBaseDirection;
    private Sprite mCannonSprite;
    private Texture mBaseTexture;
    private OrthoCamera mCamera;

    public Player(Vector2 position, Vector2 direction) {
        mBaseTexture = TextureManager.getInstance().getTextureByName(TextureManager.PLAYER_BASE);
        mBasePosition = position;
        mBaseDirection = direction;

        mCamera = new OrthoCamera();
        mCamera.resize();

        mCannonSprite = new Sprite(TextureManager.getInstance().getTextureByName(TextureManager
                .PLAYER_CANNON));
        mCannonSprite.setOrigin(mCannonSprite.getWidth() / 2, mCannonSprite.getHeight() / 2 - 20);
        mCannonSprite.setPosition(mBasePosition.x + mBaseTexture.getWidth() / 2 - TextureManager
                .getInstance().getTextureByName(TextureManager
                        .PLAYER_CANNON).getWidth() / 2, mBasePosition.y + 15);
    }

    public void setCannonRotation(float x1, float y1, float x2, float y2) {
        float normalX = x2 - x1;
        float normalY = y2 - y1;
        float mRotation = (float) ((Math.atan2(normalY, normalX) * 180 / Math.PI) + 90);
        mCannonSprite.setRotation(mRotation);
    }

    public void update() {
        onPlayerTouchedScreen();
        onPlayerFiredMissileWithKeyboard();
    }

    private void onPlayerFiredMissileWithKeyboard() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (System.currentTimeMillis() - mLastFired >= 250) {
                EntityManager.getInstance().addEntity(new Missile(new Vector2(((mBasePosition.x +
                        (mBaseTexture.getWidth() / 2)) - (TextureManager.getInstance()
                        .getTextureByName(TextureManager.MISSILE).getWidth() / 2)), mBasePosition
                        .y), mBaseDirection));
                mLastFired = System.currentTimeMillis();
            }
        }
    }

    private void onPlayerTouchedScreen() {
        if (Gdx.input.isTouched()) {
            Vector2 screenTouch = mCamera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());

            // If the touched point is not within the height of the cannon base, only then
            // process input
            if (!(screenTouch.y < mBasePosition.y + mBaseTexture.getHeight() && screenTouch.y >
                    mBasePosition.y)) {
                setCannonRotation(screenTouch.x, screenTouch.y, mBasePosition.x, mBasePosition.y);
            }
        }
    }

    public void render(SpriteBatch sb) {
        mCannonSprite.draw(sb);
        sb.draw(mBaseTexture, mBasePosition.x, mBasePosition.y);
    }
}
