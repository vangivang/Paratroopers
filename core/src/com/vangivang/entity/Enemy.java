package com.vangivang.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/18/15.
 */
public class Enemy extends Entity{

    public Enemy(Vector2 position, Vector2 direction) {
        super(TextureManager.ENEMY, position, direction);
    }

    @Override
    public void update() {
        mPosition.add(mDirection);
    }
}
