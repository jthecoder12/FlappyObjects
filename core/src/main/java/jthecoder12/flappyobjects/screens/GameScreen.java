package jthecoder12.flappyobjects.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jthecoder12.flappyobjects.components.CircleComponent;
import jthecoder12.flappyobjects.components.PositionComponent;
import jthecoder12.flappyobjects.entities.Player;

public final class GameScreen extends CommonScreen {
    @SuppressWarnings("GDXJavaStaticResource")
    public static GameScreen INSTANCE;

    public ShapeRenderer shapeRenderer;
    private Sound clickSound;
    private Player player;

    @Override
    protected void setPositions() {
        if(shapeRenderer != null) shapeRenderer.dispose();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        INSTANCE = this;

        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click-b.ogg"));
        clickSound.play();

        stage = new Stage(new ScreenViewport());
        player = new Player();
        player.getComponent(PositionComponent.class).getPosition().set(400, 400);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        player.getComponent(CircleComponent.class).render();
        shapeRenderer.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        clickSound.dispose();
        shapeRenderer.dispose();
        stage.dispose();
    }

    @Override
    public void dispose() {

    }
}
