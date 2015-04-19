package com.vangivang.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vangivang.screens.GameScreen;
import com.vangivang.screens.ScreenManager;

public class MainGame extends ApplicationAdapter {

    public static int WIDTH = 800;
    public static int HEIGHT = 480;

	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
        ScreenManager.setScreen(new GameScreen());

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().update();
        }

        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().render(batch);
        }
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
        Gdx.app.log("###", "pause in MainGame");
        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().pause();
        }
    }

    @Override
    public void resume() {
        Gdx.app.log("###", "resume in MainGame");
        if (ScreenManager.getCurrentScreen() != null){
            ScreenManager.getCurrentScreen().resume();
        }
    }
}
