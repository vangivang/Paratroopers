package com.vangivang.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.vangivang.game.MainGame;
import com.vangivang.game.TextureManager;

/**
 * Once an entity of any kind or type is added to the world, it should be handeled here.
 * Creation of the entity on the other hand, should be the responsibility of the entities conceptual
 * parent. For instance, an {@link EnemyBomb} will be created by an {@link EnemyShip}. But the
 * EnemyBomb will be
 * handled here as it is now part of the world.
 */
public class EntityManager {

    private static EntityManager mInstance = null;

    private Array<Entity> mEntities;
    private Array<EnemyBomb> mActiveEnemyBombs;
    private Player mPlayer;

    public static EntityManager getInstance() {
        if (mInstance == null) {
            mInstance = new EntityManager(10);
        }

        return mInstance;
    }

    private EntityManager(int enemyAmount) {

        mEntities = new Array<Entity>();
        mActiveEnemyBombs = new Array<EnemyBomb>();
        mPlayer = new Player(new Vector2((MainGame.WIDTH / 2) - (TextureManager.getInstance()
                .getTextureByName(TextureManager.PLAYER_BASE).getWidth() / 2), 0), new Vector2(0,
                0));

        // Add enemies in random position to entities list
        for (int i = 0; i < enemyAmount; i++) {
            float y = MathUtils.random((TextureManager.getInstance().getTextureByName
                    (TextureManager.BACKGROUND).getHeight() / 3) * 2, TextureManager.getInstance
                    ().getTextureByName(TextureManager.BACKGROUND).getHeight() -
                    TextureManager.getInstance().getTextureByName(TextureManager.ENEMY).getHeight
                            ());
            float x = MathUtils.random(MainGame.WIDTH, MainGame.WIDTH * 2);
            float speed = MathUtils.random(2, 5);
            addEntity(new EnemyShip(new Vector2(x, y), new Vector2(-speed, 0)));
        }

    }

    public void update() {
        updateEntities();
        updatePlayerMissiles();
        updateEnemyBombs();
        updatePlayer();
        checkCollisions();

    }

    public void render(SpriteBatch sb) {
        renderEntities(sb);
        renderEnemyBombs(sb);
        renderPlayer(sb);
    }

    private void updatePlayer() {
        getPlayer().update();
    }

    private void updateEnemyBombs() {
        for (EnemyBomb bomb : getActiveEnemyBombs()) {
            bomb.update();
            if (!bomb.isAlive()) {
                removeEnemyBombEntity(bomb);
            }
        }
    }

    private void updatePlayerMissiles() {
        for (Missile m : getMissiles()) {
            if (m.mPosition.y > MainGame.HEIGHT) {
                getEntities().removeValue(m, true);
            }
        }
    }

    private void updateEntities() {
        for (Entity e : getEntities()) {
            e.update();
        }
    }

    private void renderPlayer(SpriteBatch sb) {
        getPlayer().render(sb);
    }

    private void renderEnemyBombs(SpriteBatch sb) {
        for (EnemyBomb bomb : getActiveEnemyBombs()) {
            bomb.render(sb);
        }
    }

    private void renderEntities(SpriteBatch sb) {
        for (Entity e : getEntities()) {
            e.render(sb);
        }
    }

    private void checkCollisions() {
        for (EnemyShip e : getEnemies()) {
            for (Missile m : getMissiles()) {
                if (e.getBounds().overlaps(m.getBounds())) {
                    mEntities.removeValue(e, true);
                    mEntities.removeValue(m, true);
                }
            }
        }
    }

    private Player getPlayer() {
        return mPlayer;
    }

    private Array<Entity> getEntities() {
        return mEntities;
    }

    private Array<EnemyBomb> getActiveEnemyBombs() {
        return mActiveEnemyBombs;
    }

    private Array<EnemyShip> getEnemies() {
        Array<EnemyShip> ret = new Array<EnemyShip>();
        for (Entity e : getEntities()) {
            if (e instanceof EnemyShip) {
                ret.add((EnemyShip) e);
            }
        }

        return ret;
    }

    private Array<Missile> getMissiles() {
        Array<Missile> ret = new Array<Missile>();
        for (Entity e : getEntities()) {
            if (e instanceof Missile) {
                ret.add((Missile) e);
            }
        }

        return ret;
    }

    public void addEnemyBombEntity(EnemyBomb bomb) {
        getActiveEnemyBombs().add(bomb);
    }

    public void removeEnemyBombEntity(EnemyBomb bomb) {
        getActiveEnemyBombs().removeValue(bomb, true);
    }

    public void addEntity(Entity entity) {
        getEntities().add(entity);
    }

    public boolean isGameOver() {
        return getEnemies().size <= 0;
    }
}
