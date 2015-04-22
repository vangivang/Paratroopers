package com.vangivang.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by alonm on 4/14/15.
 */
public class TextureManager {

    public static final Texture PLAYER = new Texture(Gdx.files.internal("player.png"));
    public static final Texture ENEMY = new Texture(Gdx.files.internal("heli_enemy.png"));
    public static final Texture MISSILE = new Texture(Gdx.files.internal("missile.png"));
    public static final Texture BACKGROUND = new Texture(Gdx.files.internal("background.png"));
}
