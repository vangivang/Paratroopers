package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private Laser mLaser;
    private float mRotation = 0;

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

        mLaser = new Laser();
        mLaser.setDistance(500);
        mLaser.setBeamColor(Color.RED);
        mLaser.setOverlayColor(Color.WHITE);
    }

    public void setCannonRotation(float touchedX, float touchedY, float originX, float originY) {
        float normalX = originX - touchedX;
        float normalY = originY - touchedY;
        mRotation = (float) (((Math.atan2(normalY, normalX)) * 180 / Math.PI) + 90);
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
            activateBeam();
        } else {
            terminateBeam();
        }
    }

    private void activateBeam() {
        mLaser.setPosition(new Vector2(368, (mCannonSprite.getY() + mCannonSprite.getHeight() - 20)));
        mLaser.setRotation(mRotation);
        setLaserReady(true);
    }

    private void terminateBeam(){
        setLaserReady(false);
    }

    private void setLaserReady(boolean isReady){
        mLaser.setIsReady(isReady);
    }

    public void render(SpriteBatch sb) {
        mCamera.update();
        mCannonSprite.draw(sb);
        if (mLaser != null){
            mLaser.render(sb);
        }
        sb.draw(mBaseTexture, mBasePosition.x, mBasePosition.y);

    }

    public Vector2 getCannonPointOfInterestForDebug() {
        Vector3 vec = new Vector3(mCannonSprite.getVertices()[SpriteBatch.X1], mCannonSprite.getVertices()[SpriteBatch.Y1], 0);
        mCamera.project(vec);
        return new Vector2(vec.x, vec.y);
    }
}
