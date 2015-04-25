package com.vangivang.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/23/15.
 */
public class EnemyBomb extends SpriteEntity implements Pool.Poolable{

    private boolean mIsAlive;

    public EnemyBomb(int singleSpriteWidth, int singleSpriteHeight, Vector2 position, Vector2 direction) {
        super(TextureManager.ENEMY_BOMB, singleSpriteWidth, singleSpriteHeight, position, direction);
    }

    public void initBomb(float posX, float posY){
        mPosition.set(posX, posY);
        mIsAlive = true;
    }

    public boolean isAlive(){
        return mIsAlive;
    }

    public void update() {
        mPosition.add(mDirection);
        if (isOutOfScreen()){
            mIsAlive = false;
        }
    }

    private boolean isOutOfScreen(){
        return mPosition.y < -mTexture.getHeight();
    }

    @Override
    public void reset() {
        mPosition.set(0,0);
        mIsAlive = false;
    }
}
