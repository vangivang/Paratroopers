package com.vangivang.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.game.MainGame;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/18/15.
 */
public class Enemy extends Entity{

    private long mLastBombFiredTime = 0;

    public Enemy(Vector2 position, Vector2 direction) {
        super(TextureManager.ENEMY, position, direction);
    }

    @Override
    public void update() {
        mPosition.add(mDirection);
        if (mPosition.x <= -mTexture.getWidth()){
            mPosition.x = MathUtils.random(MainGame.WIDTH, MainGame.WIDTH + mTexture.getWidth());
            mPosition.y = MathUtils.random((MainGame.HEIGHT / 3) * 2, MainGame.HEIGHT - TextureManager.ENEMY.getHeight());
        }

    }

    public long getLastBombFired() {
        return mLastBombFiredTime;
    }

    public void setLastBombFired(long mLastBombFired) {
        this.mLastBombFiredTime = mLastBombFired;
    }
}
