package jthecoder12.flappyobjects.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncResult;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jthecoder12.flappyobjects.Main;

public final class LoadingScreen extends CommonScreen {
    private Label nameLabel;
    private Label infoLabel;

    private AsyncExecutor executor;
    private AsyncResult<Void> result;
    private TitleScreen titleScreen;

    @Override
    protected void setPositions() {
        nameLabel.setPosition(Gdx.graphics.getWidth() / 2f - nameLabel.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - nameLabel.getHeight() / 2f);
        infoLabel.setPosition(Gdx.graphics.getWidth() / 2f - infoLabel.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - infoLabel.getHeight() / 2f - 100);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("Tiny5/40.fnt")), Color.WHITE);

        nameLabel = new Label("JTHECODER12", labelStyle);
        infoLabel = new Label("Press R to change window size on desktop. Click to enable audio on web.", labelStyle);

        setPositions();
        stage.addActor(nameLabel);
        stage.addActor(infoLabel);

        executor = new AsyncExecutor(1, "Load Title Screen");
        titleScreen = new TitleScreen();
        result = executor.submit(() -> {
            titleScreen.init();

            return null;
        });
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        if(result.isDone()) Main.INSTANCE.setScreen(titleScreen);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        executor.dispose();
        stage.dispose();
    }

    @Override
    public void dispose() {

    }
}
