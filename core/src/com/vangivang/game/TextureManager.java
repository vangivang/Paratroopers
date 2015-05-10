package com.vangivang.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by alonm on 4/14/15.
 */
public class TextureManager {

    public static final Texture PLAYER_BASE = new Texture(Gdx.files.internal("cnn_player_base.png"));
    public static final Texture PLAYER_CANNON = new Texture(Gdx.files.internal("cnn_player_cannon.png"));
    public static final Texture ENEMY = new Texture(Gdx.files.internal("enemy_diffuse.png"));
    public static final Texture MISSILE = new Texture(Gdx.files.internal("missile.png"));
    public static final Texture BACKGROUND = new Texture(Gdx.files.internal("background.png"));

    public static final Texture ENEMY_BOMB = new Texture(Gdx.files.internal("alien_bomb_sprites.png"));
    public static final int ENEMY_BOMB_SPRITE_WIDTH = 35;
    public static final int ENEMY_BOMB_SPRITE_HEIGHT = 49;
}
