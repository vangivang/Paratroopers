package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/14/15.
 */
public class Player extends Entity {

    public Player(Vector2 position, Vector2 direction) {
        super(TextureManager.PLAYER, position, direction);
    }

    @Override
    public void update() {
        mDirection = mDirection.scl(Gdx.graphics.getDeltaTime());
        mPosition.add(mDirection);
    }
}
