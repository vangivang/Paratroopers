package com.vangivang.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.game.MainGame;

/**
 * Created by alonm on 4/23/15.
 */
public class EnemyBomb extends SpriteEntity {

    public EnemyBomb(Texture texture, int singleSpriteWidth, int singleSpriteHeight, Vector2
            position, Vector2 direction) {
        super(texture, singleSpriteWidth, singleSpriteHeight, position, direction);
    }

    @Override
    public void update() {
        mPosition.add(mDirection);
        if (mPosition.y <= -mTexture.getHeight()){
            mPosition.y = MainGame.HEIGHT;
        }
    }
}
