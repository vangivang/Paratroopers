package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/14/15.
 */
public class Player extends Entity {

    private long mLastFired = 0;

    public Player(Vector2 position, Vector2 direction) {
        super(TextureManager.PLAYER, position, direction);
    }

    @Override
    public void update() {
        mPosition.add(mDirection);

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            setDirection(300, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            setDirection(-300, 0);
        } else {
            setDirection(0, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            if (System.currentTimeMillis() - mLastFired >= 250){
                EntityManager.getInstance().addEntity(new Missle(new Vector2(((mPosition.x + (mTexture.getWidth() / 2)) - (TextureManager.MISSILE.getWidth() / 2)), mPosition.y), mDirection));
                mLastFired = System.currentTimeMillis();
            }
        }

    }
}
