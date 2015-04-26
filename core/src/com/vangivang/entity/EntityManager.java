package com.vangivang.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.vangivang.game.MainGame;
import com.vangivang.game.TextureManager;

/**
 * Created by alonm on 4/18/15.
 */
public class EntityManager {

    private static EntityManager mInstance = null;
    private static Array<Entity> mEntities;
    private Array<EnemyBomb> mActiveEnemyBombs;
    private Pool<EnemyBomb> mEnemyBombPool = new Pool<EnemyBomb>() {
        @Override
        protected EnemyBomb newObject() {
            int speed = MathUtils.random(4, 7);
            return new EnemyBomb(TextureManager.ENEMY_BOMB_SPRITE_WIDTH, TextureManager.ENEMY_BOMB_SPRITE_HEIGHT, new Vector2(0,0), new Vector2(0, -speed));
        }
    };

    private Player mPlayer;
    private Controller mController;

    public static EntityManager getInstance(){
        if (mInstance == null){
            mInstance = new EntityManager(2);
        }

        return mInstance;
    }

    private EntityManager(int enemyAmount){
        mEntities = new Array<Entity>();
        mActiveEnemyBombs = new Array<EnemyBomb>();

        mPlayer = new Player(new Vector2((MainGame.WIDTH / 2) - (TextureManager.PLAYER.getWidth() / 2),-15), new Vector2(0,0));

        for (int i = 0; i < enemyAmount; i++) {
            float y = MathUtils.random((MainGame.HEIGHT / 3) * 2, MainGame.HEIGHT - TextureManager.ENEMY.getHeight());
            float x = MathUtils.random(MainGame.WIDTH, MainGame.WIDTH * 2);
            float speed = MathUtils.random(2, 5);
            addEntity(new Enemy(new Vector2(x, y), new Vector2(-speed, 0)));
        }

        mController = new Controller(new Vector2((MainGame.WIDTH - TextureManager.CONTROLLER_BASE.getWidth()) - 18, 20));
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

        for (Enemy e : getEnemies()){
            shootEnemyBomb(e);
        }

        for (EnemyBomb bomb : mActiveEnemyBombs){
            bomb.update();
            if (!bomb.isAlive()){
                mEnemyBombPool.free(bomb);
                mActiveEnemyBombs.removeValue(bomb, false);
            }
        }

        mController.update();
        mPlayer.update();
        checkColissions();

    }

    public void render(SpriteBatch sb){
        for (Entity e : mEntities){
            e.render(sb);
        }

        for (EnemyBomb bomb : mActiveEnemyBombs){
            bomb.render(sb);
        }
        mPlayer.render(sb);
        mController.render(sb);
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

    private void shootEnemyBomb(Enemy shootingEnemy){
        if (System.currentTimeMillis() - shootingEnemy.getLastBombFired() >= 1000){
            EnemyBomb bomb = mEnemyBombPool.obtain();
            bomb.initBomb(shootingEnemy.mPosition.x + shootingEnemy.mTexture.getWidth() / 2 - TextureManager.ENEMY_BOMB_SPRITE_WIDTH / 2, shootingEnemy.mPosition.y);
            mActiveEnemyBombs.add(bomb);
            shootingEnemy.setLastBombFired(System.currentTimeMillis());
        }
    }

    public boolean isGameOver(){
        return getEnemies().size <= 0;
    }
}
