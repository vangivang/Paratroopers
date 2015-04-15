package com.vangivang.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alonm on 4/14/15.
 */
public abstract class Entity {

    protected Texture mTexture;
    protected Vector2 mPosition;
    protected Vector2 mDirection;

    public Entity(Texture texture, Vector2 position, Vector2 direction){
        mTexture = texture;
        mPosition = position;
        mDirection = direction;
    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public abstract void update();

    public void render(SpriteBatch sb){
        sb.draw(mTexture, mPosition.x, mPosition.y);
    }

    public void setDirection(float x, float y){
        mDirection.set(x, y);
    }
}
