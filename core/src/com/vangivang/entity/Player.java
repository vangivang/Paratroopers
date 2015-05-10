package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    private OrthographicCamera mCamera;

    public Player(Vector2 position, Vector2 direction) {
        mPosition = position;
        mDirection = direction;
        mCannonSprite = new Sprite(TextureManager.PLAYER_CANNON);
        mCannonSprite.setOrigin(mCannonSprite.getWidth() / 2, mCannonSprite.getHeight() / 2 - 20);
        mBase = TextureManager.PLAYER_BASE;
        mCamera = new OrthographicCamera();
    }

    public void setRotation(float x1, float y1, float x2, float y2){

        double o1 = Math.atan2(y1, x1);
        double o2 = Math.atan2(y2, x2);

        float f1 = (float) (o1 * (180 / Math.PI));
        float f2 = (float) (o2 * (180 / Math.PI));

        mRotation = f1+ f2;
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
            Vector3 vector = mCamera.project(new Vector3(touchX, touchY, 0));
            setRotation(vector.x, vector.y, mPosition.x, mPosition.y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            if (System.currentTimeMillis() - mLastFired >= 250){
                EntityManager.getInstance().addEntity(new Missle(new Vector2(((mPosition.x + (mBase.getWidth() / 2)) - (TextureManager.MISSILE.getWidth() / 2)), mPosition.y), mDirection));
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
