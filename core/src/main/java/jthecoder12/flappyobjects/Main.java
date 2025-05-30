package jthecoder12.flappyobjects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public final class Main extends Game {
    @Override
    public void create() {
        setScreen(new LoadingScreen());
    }

    @Override
    public void render() {
        super.render();

        // If R is pressed and the backend is LWJGL3 (we check using getCanonicalName() as instanceof requires the actual class to be passed in which may not work on the GWT version) and then sets the resolution to 1280x720
        if(Gdx.input.isKeyJustPressed(Input.Keys.R) && Gdx.graphics.getClass().getCanonicalName().equals("com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics"))
            if(Gdx.graphics.getWidth() == 1280) Gdx.graphics.setWindowedMode(1920, 1080);
            else Gdx.graphics.setWindowedMode(1280, 720);
    }
}
