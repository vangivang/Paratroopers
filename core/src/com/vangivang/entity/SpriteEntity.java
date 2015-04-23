package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alonm on 4/23/15.
 */
public abstract class SpriteEntity extends Entity {

    protected TextureRegion[] mAnimationFrames;
    protected Animation mAnimation;
    private float mElapsedTime;

    public SpriteEntity(Texture texture, int singleSpriteWidth, int singleSpriteHeight, Vector2 position, Vector2 direction) {
        super(texture, position, direction);

        int textureWidth = mTexture.getWidth();
        int columnN = textureWidth / singleSpriteWidth;
        int regionWidth = 0;
        mAnimationFrames = new TextureRegion[columnN];
        TextureRegion region;

        for (int i = 0; i < columnN; i++) {
            region = new TextureRegion(mTexture, regionWidth, 0, singleSpriteWidth, singleSpriteHeight);
            regionWidth+= singleSpriteWidth;
            mAnimationFrames[i] = region;
        }

        mAnimation = new Animation(1/9f, mAnimationFrames);
    }

    public void render(SpriteBatch sb){
        mElapsedTime += Gdx.graphics.getDeltaTime();
        sb.draw(mAnimation.getKeyFrame(mElapsedTime, true), mPosition.x, mPosition.y);
    }
}
