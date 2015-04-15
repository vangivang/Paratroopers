package com.vangivang.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vangivang.screens.MenuScreen;
import com.vangivang.screens.ScreenManager;

public class MainGame extends ApplicationAdapter {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
        ScreenManager.setScreen(new MenuScreen());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().update();
        }

        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().render(batch);
        }
		batch.begin();
		batch.end();
	}

    @Override
    public void dispose() {
        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().dispose();
        }
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().resize(width, height);
        }
    }

    @Override
    public void pause() {
        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().pause();
        }
    }

    @Override
    public void resume() {
        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().resume();
        }
    }
}
