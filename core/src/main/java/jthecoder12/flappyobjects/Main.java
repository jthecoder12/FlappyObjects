package jthecoder12.flappyobjects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import jthecoder12.flappyobjects.screens.LoadingScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public final class Main extends Game {
    public static Main INSTANCE;

    public BitmapFont p40;
    public BitmapFont p60;
    private Sound startupSound;
    private Music music;
    private boolean waitingForMusic = true;
    private final Array<Screen> screens = new Array<>();

    @Override
    public void create() {
        INSTANCE = this;

        p40 = new BitmapFont(Gdx.files.internal("Tiny5/40.fnt"));
        p60 = new BitmapFont(Gdx.files.internal("Tiny5/60.fnt"));
        setScreen(new LoadingScreen());
        startupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/beep-03.wav"));
        startupSound.play(0.3f);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/chill_wave_3_mastered.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
    }

    @Override
    public void render() {
        super.render();

        // If R is pressed and the backend is LWJGL3 (we check using getSimpleName() as instanceof requires the actual class to be passed in which may not work on the GWT version) and then sets the resolution to 1280x720
        if(Gdx.input.isKeyJustPressed(Input.Keys.R) && Gdx.graphics.getClass().getSimpleName().equals("Lwjgl3Graphics") && !getScreen().getClass().getSimpleName().equals("GameScreen"))
            if(Gdx.graphics.getWidth() == 1280) Gdx.graphics.setWindowedMode(1920, 1080);
            else Gdx.graphics.setWindowedMode(1280, 720);

        if(getScreen().getClass().getSimpleName().equals("TitleScreen") && waitingForMusic) {
            waitingForMusic = false;
            music.play();
        }
    }

    @SuppressWarnings("GDXJavaUnsafeIterator")
    @Override
    public void dispose() {
        super.dispose();

        for(Screen screen1 : screens) screen1.dispose();
        p40.dispose();
        p60.dispose();
        music.stop();
        music.dispose();
        startupSound.dispose();
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
        screens.add(screen);
    }
}
