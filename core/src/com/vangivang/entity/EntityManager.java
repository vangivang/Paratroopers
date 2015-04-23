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
        mPlayer = new Player(new Vector2((MainGame.WIDTH / 2) - (TextureManager.PLAYER.getWidth() / 2),-15), new Vector2(0,0), this);
        for (int i = 0; i < enemyAmount; i++) {
            float y = MathUtils.random((MainGame.HEIGHT / 3) * 2, MainGame.HEIGHT - TextureManager.ENEMY.getHeight());
            float x = MathUtils.random(MainGame.WIDTH, MainGame.WIDTH * 2);
            float speed = MathUtils.random(2, 5);
            addEntity(new Enemy(new Vector2(x, y), new Vector2(-speed, 0)));
        }

        float x = 0;
        float bombsSpeed;
        for (int i = 0; i < 6; i++) {
            bombsSpeed = MathUtils.random(4.5f, 7);
            x += 120;
            addEntity(new EnemyBomb(TextureManager.ENEMY_BOMB, 35, 49, new Vector2(x, MainGame.HEIGHT), new Vector2(0, -bombsSpeed)));
        }
    }

    public void update(){
        for (Entity e : mEntities){
            e.update();
        }

        for (Missle m : getMissiles()){
            if (m.mPosition.y > MainGame.HEIGHT){
                mEntities.removeValue(m, false);
            }
        }

        mPlayer.update();
        checkColissions();

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

    private void checkColissions(){
        for (Enemy e : getEnemies()){
            for (Missle m : getMissiles()){
                if (e.getBounds().overlaps(m.getBounds())){
                    mEntities.removeValue(e, false);
                    mEntities.removeValue(m, false);
                }
            }
        }
    }

    public Array<Enemy> getEnemies(){
        Array<Enemy> ret = new Array<Enemy>();
        for (Entity e : mEntities){
            if (e instanceof Enemy){
                ret.add((Enemy) e);
            }
        }

        return ret;
    }

    public Array<Missle> getMissiles(){
        Array<Missle> ret = new Array<Missle>();
        for (Entity e : mEntities){
            if (e instanceof Missle){
                ret.add((Missle) e);
            }
        }

        return ret;
    }

    public boolean isGameOver(){
        return getEnemies().size <= 0;
    }
}
