package com.vangivang.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.vangivang.game.MainGame;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/18/15.
 */
public class EntityManager {

    private final Array<Entity> mEntities = new Array<Entity>();
    private Player mPlayer;

    public EntityManager(int enemyAmount){
        mPlayer = new Player(new Vector2(240,15), new Vector2(0,0));
        for (int i = 0; i < enemyAmount; i++) {
            float y = MathUtils.random((MainGame.HEIGHT / 3) * 2, MainGame.HEIGHT - TextureManager.ENEMY.getHeight());
            float x = MathUtils.random(MainGame.WIDTH, MainGame.WIDTH * 2);
            float speed = MathUtils.random(2, 5);
            addEntity(new Enemy(new Vector2(x, y), new Vector2(-speed, 0)));
        }
    }

    public void update(){
        for (Entity e : mEntities){
            e.update();
        }

        mPlayer.update();

    }

    public void render(SpriteBatch sb){
        for (Entity e : mEntities){
            e.render(sb);
        }

        mPlayer.render(sb);
    }

    public void addEntity(Entity entity){
        mEntities.add(entity);
    }
}
