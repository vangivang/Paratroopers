package com.vangivang.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alonm on 4/14/15.
 */
public class TextureManager {

    private static TextureManager mInstance = null;

    public static final String PLAYER_BASE = "PLAYER_BASE";
    public static final String PLAYER_CANNON = "PLAYER_CANNON";
    public static final String ENEMY = "ENEMY";
    public static final String MISSILE = "MISSILE";
    public static final String BACKGROUND = "BACKGROUND";
    public static final String ENEMY_BOMB = "ENEMY_BOMB";

    public static final int ENEMY_BOMB_SPRITE_WIDTH = 35;
    public static final int ENEMY_BOMB_SPRITE_HEIGHT = 49;

    private static Map<String, Texture> mTextures = new HashMap<>();

    /**
     * To add more textures, check this classes constructor
     * @return TextureManager singleton instance
     */
    public static TextureManager getInstance(){
        if (mInstance == null){
            mInstance = new TextureManager();
        }

        return  mInstance;
    }

    private TextureManager() {
        Map<String, Texture> tempMap = new HashMap<>();
        tempMap.put(PLAYER_BASE, new Texture(Gdx.files.internal("cnn_player_base.png")));
        tempMap.put(PLAYER_CANNON, new Texture(Gdx.files.internal("cnn_player_cannon.png")));
        tempMap.put(ENEMY, new Texture(Gdx.files.internal("enemy_diffuse.png")));
        tempMap.put(MISSILE, new Texture(Gdx.files.internal("missile.png")));
        tempMap.put(BACKGROUND, new Texture(Gdx.files.internal("background.png")));
        tempMap.put(ENEMY_BOMB, new Texture(Gdx.files.internal("alien_bomb_sprites.png")));
        mTextures = Collections.unmodifiableMap(tempMap);
    }

    public Texture getTextureByName(String textureName){
        Texture texture = mTextures.get(textureName);
        if (texture == null){
            throw new NullPointerException("Could not find texture under that name. Check map init");
        }

        return texture;
    }

    public void dispose(){
        for (Texture texture : mTextures.values()){
            texture.dispose();
        }
    }
}
