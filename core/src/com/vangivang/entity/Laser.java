package com.vangivang.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 5/23/15.
 */
public class Laser {

    private Vector2 mPosition = new Vector2();
    private float mDistance;

    private Color mColor = new Color(Color.RED);
    private Color mRayColor = new Color(Color.WHITE);

    public float mRotation;

    private Sprite begin1;
    private Sprite begin2;
    private Sprite mid1;
    private Sprite mid2;
    private Sprite end1;
    private Sprite end2;
    private boolean mIsReady;

    public Laser() {
        loadTextures();
        setLaserColors();
        setLaserOrigin();
    }

    public void render(SpriteBatch spriteBatch) {
        if (mIsReady) {
            setLaserLength();
            updateLaserPosition();
            updateLaserRotation();
            drawLaser(spriteBatch);
        }
    }

    private void setLaserLength() {
        mid1.setSize(mid1.getWidth(), mDistance);
        mid2.setSize(mid1.getWidth(), mDistance);
    }

    private void setLaserColors() {
        begin1.setColor(mColor);
        begin2.setColor(mRayColor);
        mid1.setColor(mColor);
        mid2.setColor(mRayColor);
        end1.setColor(mColor);
        end2.setColor(mRayColor);
    }

    private void setLaserOrigin() {
        begin1.setOrigin(begin1.getWidth() / 2, 32);
        begin2.setOrigin(begin1.getWidth() / 2, 32);
        mid1.setOrigin(mid1.getWidth() / 2, -begin1.getHeight() + 32);
        mid2.setOrigin(mid2.getWidth() / 2, -begin1.getHeight() + 32);
        end1.setOrigin(mid1.getWidth() / 2, (-begin1.getHeight() - mid1.getHeight()) + 32);
        end2.setOrigin(mid2.getWidth() / 2, (-begin1.getHeight() - mid2.getHeight()) + 32);
    }

    private void loadTextures() {
        begin1 = new Sprite(TextureManager.getInstance().getTextureByName(TextureManager.BEAM_START_BG));
        begin2 = new Sprite(TextureManager.getInstance().getTextureByName(TextureManager.BEAM_START_OVERLAY));
        mid1 = new Sprite(TextureManager.getInstance().getTextureByName(TextureManager.BEAM_MID_BG));
        mid2 = new Sprite(TextureManager.getInstance().getTextureByName(TextureManager.BEAM_MID_OVERLAY));
        end1 = new Sprite(TextureManager.getInstance().getTextureByName(TextureManager.BEAM_END_BG));
        end2 = new Sprite(TextureManager.getInstance().getTextureByName(TextureManager.BEAM_END_OVERLAY));
    }

    private void drawLaser(SpriteBatch spriteBatch) {
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);

        begin1.draw(spriteBatch);
        begin2.draw(spriteBatch);
        mid1.draw(spriteBatch);
        mid2.draw(spriteBatch);
        end1.draw(spriteBatch);
        end2.draw(spriteBatch);

        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    private void updateLaserRotation() {
        begin1.setRotation(mRotation);
        begin2.setRotation(mRotation);
        mid1.setRotation(mRotation);
        mid2.setRotation(mRotation);
        end1.setRotation(mRotation);
        end2.setRotation(mRotation);
    }

    private void updateLaserPosition() {
        begin1.setPosition(mPosition.x, mPosition.y);
        begin2.setPosition(mPosition.x, mPosition.y);
        mid1.setPosition(begin1.getX(), begin1.getY() + begin1.getHeight());
        mid2.setPosition(begin1.getX(), begin1.getY() + begin1.getHeight());
        end1.setPosition(begin1.getX(), begin1.getY() + begin1.getHeight() + mid1.getHeight());
        end2.setPosition(begin1.getX(), begin1.getY() + begin1.getHeight() + mid1.getHeight());
    }

    public void setIsReady(boolean isReady) {
        mIsReady = isReady;
    }

    public void setPosition(Vector2 position) {
        mPosition = position;
    }

    public void setDistance(float distance) {
        mDistance = distance;
    }

    public void setBeamColor(Color beamColor) {
        mColor = beamColor;
    }

    public void setOverlayColor(Color overlayColor) {
        mRayColor = overlayColor;
    }

    public void setRotation(float rotation) {
        mRotation = rotation;
    }
}
