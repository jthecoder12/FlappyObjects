package jthecoder12.flappyobjects.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jthecoder12.flappyobjects.Main;

public final class TitleScreen extends CommonScreen {
    private Label titleLabel;
    private ImageButton playButton;
    private final Array<Texture> textures = new Array<>();

    void init() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(Main.INSTANCE.p60, Color.WHITE);
        titleLabel = new Label("Flappy Objects", labelStyle);
    }

    @Override
    protected void setPositions() {
        titleLabel.setPosition(Gdx.graphics.getWidth() / 2f - titleLabel.getWidth() / 2f, Gdx.graphics.getHeight() - titleLabel.getHeight());
        playButton.setPosition(Gdx.graphics.getWidth() / 2f - playButton.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - playButton.getHeight() / 2f + 100);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Textures can't be created in the async init method
        Texture playButtonTexture = new Texture(Gdx.files.internal("textures/buttons/playbutton.png"));
        textures.add(playButtonTexture);
        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButtonTexture)));
        playButton.setSize(playButton.getWidth() / 1.5f, playButton.getHeight() / 1.5f);
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(button == 0) Main.INSTANCE.setScreen(new GameScreen());

                return true;
            }
        });

        setPositions();
        stage.addActor(titleLabel);
        stage.addActor(playButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @SuppressWarnings("GDXJavaUnsafeIterator")
    @Override
    public void hide() {
        for(Texture texture : textures) texture.dispose();
        stage.dispose();
    }

    @Override
    public void dispose() {

    }
}
