package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vangivang.camera.OrthoCamera;

/**
 * Created by alonm on 4/25/15.
 */
public class Controller implements InputProcessor{

    private Vector2 mBasePosition;
    private Vector2 mKnobDirection;
    private Vector2 mKnobPosition;
    private Vector3 mKnobUpdates;
    private Texture mBase;
    private Texture mKnob;
    private Sprite mKnobSprite;
    private Actor mKnobActor;
    private OrthoCamera mCamera;
    private Player mPlayer;

    public Controller(Player player, Vector2 position) {
        mBase = new Texture(Gdx.files.internal("controller_base.png"));
        mKnob = new Texture(Gdx.files.internal("controller_knob.png"));
        mKnobSprite = new Sprite(mKnob);
        mBasePosition = position;
        resetKnobPosition();
        mCamera = new OrthoCamera();
        mKnobUpdates = new Vector3();
        mPlayer = player;
        Gdx.input.setInputProcessor(this);
//        mKnobSprite.
    }

    private void resetKnobPosition() {
        mKnobDirection = new Vector2(0, 0);
        mKnobPosition = new Vector2(mBasePosition.x + ((mBase.getWidth() / 2) - (mKnob.getWidth() / 2)), ((mBasePosition.y + (mKnob.getHeight() / 2)) - 4));
    }

    public void update(){
        updateKnobDirection();
    }

    public void render(SpriteBatch sb){
        sb.draw(mBase, mBasePosition.x, mBasePosition.y);
        sb.draw(mKnob, mKnobPosition.x, mKnobPosition.y);
    }

    public void setCamera(OrthoCamera camera){
        mCamera = camera;
    }

    public void updateKnobDirection(){
        if (Gdx.input.isTouched()){
            int touchX = Gdx.input.getX();
            int touchY = Gdx.input.getY();
            Vector2 vector = mCamera.unprojectCoordinates(touchX, touchY);
//            if (getBounds().contains(vector.x, vector.y)){
//                int side = 2;
////                float x2 = mBasePosition.x + mBase.getWidth() / 2 - touchX;
//                mKnobPosition.set(vector.x, mKnobPosition.y);
////                mPlayer.setRotation(touchX, touchY, touchX + x2, touchY);
//                if (vector.x < mBasePosition.x + mBase.getWidth() / 2){
//                    side = 0;
//                } else if (vector.x > mBasePosition.x + mBase.getWidth() / 2){
//                    side = 1;
//                }

//            }
//                mPlayer.setRotation(getAngle(touchY, touchX));
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(mBasePosition.x, mBasePosition.y, mBase.getWidth() - mKnob.getWidth(), mBase.getHeight());
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        int touchX = Gdx.input.getX();
//        int touchY = Gdx.input.getY();
//        Vector2 vector = mCamera.unprojectCoordinates(screenX, screenY);
//        if (getBounds().contains(vector.x, vector.y)){
//            float x2 = mBasePosition.x + mBase.getWidth() / 2 - screenX;
//            mKnobPosition.set(vector.x, mKnobPosition.y);
//            mPlayer.setRotation(screenX, screenY, screenX + x2,screenY);
//        }
        return false;
    }

    public double getAngle(float x, float y)
    {
        return 1.5 * Math.PI - Math.atan2(y,x); //note the atan2 call, the order of paramers is y then x
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        resetKnobPosition();
        Gdx.app.log("TAG", "touchdown");
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
