package jthecoder12.flappyobjects.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class CommonScreen implements Screen {
    protected Stage stage;

    protected abstract void setPositions();

    @Override
    public void resize(int width, int height) {
        if(width <= 0 || height <= 0) return;
        stage.getViewport().update(width, height, true);
        setPositions();
    }

    @Override
    public void dispose() {
        hide();
    }
}
