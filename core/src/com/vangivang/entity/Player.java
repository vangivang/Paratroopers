package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.vangivang.game.MainGame;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/14/15.
 */
public class Player {

    private long mLastFired = 0;
    private Vector2 mBasePosition;
    private Vector2 mBaseDirection;
    private Sprite mCannonSprite;
    private Texture mBaseTexture;
    private OrthographicCamera mCamera;

    public Player(Vector2 position, Vector2 direction) {
        mBaseTexture = TextureManager.getInstance().getTextureByName(TextureManager.PLAYER_BASE);
        mBasePosition = position;
        mBaseDirection = direction;

        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);

        mCannonSprite = new Sprite(TextureManager.getInstance().getTextureByName(TextureManager
                .PLAYER_CANNON));
        mCannonSprite.setOrigin(mCannonSprite.getWidth() / 2, mCannonSprite.getHeight() / 2 - 20);
        mCannonSprite.setPosition(mBasePosition.x + mBaseTexture.getWidth() / 2 - TextureManager
                .getInstance().getTextureByName(TextureManager
                        .PLAYER_CANNON).getWidth() / 2, mBasePosition.y + 15);
    }

    public void setCannonRotation(float touchedX, float touchedY, float originX, float originY) {
        float normalX = originX - touchedX;
        float normalY = originY - touchedY;
        float mRotation = (float) (((Math.atan2(normalY, normalX)) * 180 / Math.PI) + 90);
        mCannonSprite.setRotation(mRotation);
    }

    public void update() {
        onPlayerTouchedScreen();
        onPlayerFiredMissileWithKeyboard();

    }

    private void onPlayerFiredMissileWithKeyboard() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (System.currentTimeMillis() - mLastFired >= 250) {
                EntityManager.getInstance().addEntity(new Missile(new Vector2(((mBasePosition.x +
                        (mBaseTexture.getWidth() / 2)) - (TextureManager.getInstance()
                        .getTextureByName(TextureManager.MISSILE).getWidth() / 2)), mBasePosition
                        .y), mBaseDirection));
                mLastFired = System.currentTimeMillis();
            }
        }
    }

    private void onPlayerTouchedScreen() {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mCamera.unproject(touchPos);
            setCannonRotation(touchPos.x, touchPos.y, mBasePosition.x + mBaseTexture.getWidth() / 2, mBasePosition.y);
        }
    }

    public void render(SpriteBatch sb) {
        mCamera.update();
        mCannonSprite.draw(sb);
        sb.draw(mBaseTexture, mBasePosition.x, mBasePosition.y);
    }
}
