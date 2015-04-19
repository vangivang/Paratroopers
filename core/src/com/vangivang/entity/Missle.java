package com.vangivang.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/19/15.
 */
public class Missle extends Entity{


    public Missle(Vector2 position, Vector2 direction) {
        super(TextureManager.MISSILE, position, new Vector2(0, 5));
    }

    @Override
    public void update() {
        mPosition.add(mDirection);
    }


}
