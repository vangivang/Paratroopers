package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.vangivang.game.EnvironmentSettings;
import com.vangivang.game.MainGame;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/18/15.
 */
public class EnemyShip extends Entity{

    private long mLastBombFiredTime = 0;
    private Pool<EnemyBomb> mEnemyBombPool = new Pool<EnemyBomb>() {
        @Override
        protected EnemyBomb newObject() {
            int speed = MathUtils.random(EnvironmentSettings.DROP_BOMB_SPEED_LOWER_LIMIT, EnvironmentSettings.DROP_BOMB_SPEED_UPPER_LIMIT);
            return new EnemyBomb(TextureManager.ENEMY_BOMB_SPRITE_WIDTH, TextureManager.ENEMY_BOMB_SPRITE_HEIGHT, new Vector2(0,0), new Vector2(0, -speed));
        }
    };

    public EnemyShip(Vector2 position, Vector2 direction) {
        super(TextureManager.getInstance().getTextureByName(TextureManager.ENEMY), position, direction);
    }

    @Override
    public void update() {
        mPosition.add(mDirection);
        if (mPosition.x <= -mTexture.getWidth()){
            mPosition.x = MathUtils.random(MainGame.WIDTH, MainGame.WIDTH + mTexture.getWidth());

            mPosition.y = MathUtils.random((TextureManager.getInstance().getTextureByName
                    (TextureManager.BACKGROUND).getHeight() / 3) * 2, TextureManager.getInstance
                    ().getTextureByName(TextureManager.BACKGROUND).getHeight() -
                    TextureManager.getInstance().getTextureByName(TextureManager.ENEMY).getHeight
                            ());
        }

        dropBomb();
    }


    public void dropBomb(){
        if (System.currentTimeMillis() - getLastBombFired() >= EnvironmentSettings.DROP_BOMB_INTERVAL){
            EnemyBomb bomb = mEnemyBombPool.obtain();
            bomb.initBomb(mPosition.x + mTexture.getWidth() / 2 - TextureManager
                    .ENEMY_BOMB_SPRITE_WIDTH / 2, mPosition.y);
            EntityManager.getInstance().addEnemyBombEntity(bomb);
            setLastBombFired(System.currentTimeMillis());
        }

    }

    public long getLastBombFired() {
        return mLastBombFiredTime;
    }

    public void setLastBombFired(long mLastBombFired) {
        this.mLastBombFiredTime = mLastBombFired;
    }
}
