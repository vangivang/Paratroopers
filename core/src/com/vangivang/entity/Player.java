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
public class Player  {

    private long mLastFired = 0;
    private Vector2 mPosition;
    private Vector2 mDirection;
    private Sprite mCannonSprite;
    private Texture mBase;
    private double mRotation = 0;
    private OrthoCamera mCamera;

    public Player(Vector2 position, Vector2 direction) {
        mPosition = position;
        mDirection = direction;
        mCannonSprite = new Sprite(TextureManager.PLAYER_CANNON);
        mCannonSprite.setOrigin(mCannonSprite.getWidth() / 2, mCannonSprite.getHeight() / 2 - 20);
        mBase = TextureManager.PLAYER_BASE;
        mCamera = new OrthoCamera();
    }

    public void setRotation(float x1, float y1, float x2, float y2){

        float normalX = x2 - x1;
        float normalY = y2 - y1;
        mRotation = Math.atan2(normalY, normalX) * 180 / Math.PI;

    }

    public void update() {
        mPosition.add(mDirection);

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            mRotation -= 3;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            mRotation += 3;
        }

        if (Gdx.input.isTouched()){
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
            Vector2 vector = mCamera.unprojectCoordinates(touchX, touchY);
            setRotation(vector.x, vector.y, mPosition.x, mPosition.y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            if (System.currentTimeMillis() - mLastFired >= 250){
                EntityManager.getInstance().addEntity(new Missile(new Vector2(((mPosition.x + (mBase.getWidth() / 2)) - (TextureManager.MISSILE.getWidth() / 2)), mPosition.y), mDirection));
                mLastFired = System.currentTimeMillis();
            }
        }

        mCannonSprite.setPosition(mPosition.x + mBase.getWidth() / 2 - TextureManager.PLAYER_CANNON.getWidth() / 2, mPosition.y + 15);
        mCannonSprite.setRotation((float) mRotation);
    }

    public void render(SpriteBatch sb){
        mCannonSprite.draw(sb);
        sb.draw(mBase, mPosition.x, mPosition.y);
    }
}
