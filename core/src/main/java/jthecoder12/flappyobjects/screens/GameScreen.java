package jthecoder12.flappyobjects.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    public boolean running = true;
    public Label scoreLabel;
    private Label loseLabel, pauseLabel;
    private ImageButton menuButton;
    private Sound clickSound;
    private boolean lost = false;

    private World world;

    private final Array<PipeGroup> pipeGroups = new Array<>();
    private final Array<Texture> textures = new Array<>();

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
        Gdx.input.setInputProcessor(stage);

        player = new Player(world);

        Label.LabelStyle labelStyle = new Label.LabelStyle(Main.INSTANCE.p40, Color.WHITE);
        scoreLabel = new Label("Score: 0", labelStyle);
        scoreLabel.setY(Gdx.graphics.getHeight() - scoreLabel.getHeight());
        stage.addActor(scoreLabel);

        Label.LabelStyle bigLabelStyle = new Label.LabelStyle(Main.INSTANCE.p60, Color.WHITE);
        loseLabel = new Label("You Lose", bigLabelStyle);
        loseLabel.setPosition(Gdx.graphics.getWidth() / 2f - loseLabel.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - loseLabel.getHeight() / 2f);
        loseLabel.setVisible(false);
        stage.addActor(loseLabel);

        pauseLabel = new Label("Paused", bigLabelStyle);
        pauseLabel.setPosition(Gdx.graphics.getWidth() / 2f - pauseLabel.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - pauseLabel.getHeight() / 2f);
        pauseLabel.setVisible(false);
        stage.addActor(pauseLabel);

        Texture menuButtonTexture = new Texture(Gdx.files.internal("textures/buttons/menubutton.png"));
        textures.add(menuButtonTexture);
        menuButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(menuButtonTexture)));
        menuButton.setSize(menuButton.getWidth() / 1.5f, menuButton.getHeight() / 1.5f);
        menuButton.setPosition(Gdx.graphics.getWidth() / 2f - menuButton.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - menuButton.getHeight() / 2f - 100);
        menuButton.setVisible(false);
        menuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(button == 0) {
                    TitleScreen titleScreen = new TitleScreen();
                    titleScreen.init();
                    Main.INSTANCE.setScreen(titleScreen);
                }

                return true;
            }
        });
        stage.addActor(menuButton);

        spawnNewPipe();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        world.step(1/120f, 6, 2);

        if(!pauseLabel.isVisible()) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            player.update();
            shapeRenderer.end();
        }

        Array.ArrayIterator<PipeGroup> iterator = pipeGroups.iterator();
        while(iterator.hasNext()) {
            PipeGroup current = iterator.next();

            if(current.getX() < 0) {
                current.dispose();
                iterator.remove();
            }
        }

        if(running) {
            if(!pauseLabel.isVisible()) {
                stage.getBatch().begin();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                for(PipeGroup pipeGroup : pipeGroups) pipeGroup.update(stage.getBatch());
                shapeRenderer.end();
                stage.getBatch().end();
            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                pauseLabel.setVisible(!pauseLabel.isVisible());
                menuButton.setVisible(!menuButton.isVisible());
            }
        } else if(!lost) {
            lost = true;
            pauseLabel.setVisible(false);
            loseLabel.setVisible(true);
            menuButton.setVisible(true);
        }

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
        for(Texture texture : textures) texture.dispose();
        for(PipeGroup pipeGroup : pipeGroups) pipeGroup.dispose();
        player.dispose();
        shapeRenderer.dispose();
        stage.dispose();
    }

    @Override
    public void dispose() {

    }
}
