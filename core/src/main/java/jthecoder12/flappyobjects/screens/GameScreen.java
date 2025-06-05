package jthecoder12.flappyobjects.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jthecoder12.flappyobjects.Main;
import jthecoder12.flappyobjects.entities.pipe.PipeGroup;
import jthecoder12.flappyobjects.entities.Player;

@SuppressWarnings("GDXJavaUnsafeIterator")
public final class GameScreen extends CommonScreen {
    @SuppressWarnings("GDXJavaStaticResource")
    public static GameScreen INSTANCE;

    public ShapeRenderer shapeRenderer;
    public Player player;
    public int score;
    public Label scoreLabel;
    private Sound clickSound;

    private World world;

    private final Array<PipeGroup> pipeGroups = new Array<>();

    public void spawnNewPipe() {
        pipeGroups.add(new PipeGroup());
    }

    @Override
    protected void setPositions() {
        if(shapeRenderer != null) shapeRenderer.dispose();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        INSTANCE = this;

        score = 0;

        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click-b.ogg"));
        clickSound.play();

        world = new World(new Vector2(0, -400), true);

        stage = new Stage(new ScreenViewport());
        player = new Player(world);

        Label.LabelStyle labelStyle = new Label.LabelStyle(Main.INSTANCE.p40, Color.WHITE);
        scoreLabel = new Label("Score: 0", labelStyle);
        scoreLabel.setY(Gdx.graphics.getHeight() - scoreLabel.getHeight());
        stage.addActor(scoreLabel);

        spawnNewPipe();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        world.step(1/120f, 6, 2);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        player.update();
        shapeRenderer.end();

        Array.ArrayIterator<PipeGroup> iterator = pipeGroups.iterator();
        while(iterator.hasNext()) {
            PipeGroup current = iterator.next();

            if(current.getX() < 0) {
                current.dispose();
                iterator.remove();
            }
        }

        stage.getBatch().begin();
        for(PipeGroup pipeGroup : pipeGroups) pipeGroup.update(stage.getBatch());
        stage.getBatch().end();

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
        world.dispose();
        for(PipeGroup pipeGroup : pipeGroups) pipeGroup.dispose();
        player.dispose();
        shapeRenderer.dispose();
        stage.dispose();
    }

    @Override
    public void dispose() {

    }
}
