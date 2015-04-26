package com.vangivang.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vangivang.camera.OrthoCamera;

/**
 * Created by alonm on 4/25/15.
 */
public class Controller {

    private OrthographicCamera mCamera;

    private Vector2 mBasePosition;
    private Vector2 mKnobDirection;
    private Vector2 mKnobPosition;
    private Vector3 mKnobUpdates;
    private Texture mBase;
    private Texture mKnob;
    private Actor mKnobActor;

    public Controller(Vector2 position) {
        mBase = new Texture(Gdx.files.internal("controller_base.png"));
        mKnob = new Texture(Gdx.files.internal("controller_knob.png"));
        mBasePosition = position;
        mKnobDirection = new Vector2(0, 0);
        mKnobPosition = new Vector2(mBasePosition.x + ((mBase.getWidth() / 2) - (mKnob.getWidth() / 2)) +
                mKnobDirection.x, ((mBasePosition.y + (mKnob.getHeight() / 2)) - 4) + mKnobDirection.y);
        mCamera = new OrthoCamera();
        mKnobUpdates = new Vector3();
    }

    public void update(){
        updateKnobDirection();
    }

    public void render(SpriteBatch sb){
        sb.draw(mBase, mBasePosition.x, mBasePosition.y);
        sb.draw(mKnob, mBasePosition.x + ((mBase.getWidth() / 2) - (mKnob.getWidth() / 2)) +
                mKnobDirection.x, ((mBasePosition.y + (mKnob.getHeight() / 2)) - 4) + mKnobDirection.y);
    }

    public void updateKnobDirection(){
        if (Gdx.input.isTouched()){
            int touchX = Gdx.input.getX();
            int touchY = Gdx.input.getY();
            mKnobUpdates.set(touchX, touchY, 0);
            mCamera.unproject(mKnobUpdates);
            if (getBounds().contains(mKnobUpdates.x, mKnobUpdates.y * -1)){
                mKnobDirection.set(mKnobUpdates.x, mKnobUpdates.y);
            }
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(0, 0, mKnob.getWidth(), mKnob.getHeight());
    }
}
