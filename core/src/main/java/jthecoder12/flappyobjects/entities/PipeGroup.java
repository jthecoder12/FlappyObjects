package jthecoder12.flappyobjects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import jthecoder12.flappyobjects.components.PositionComponent;
import jthecoder12.flappyobjects.components.TextureComponent;

public final class PipeGroup implements Disposable {
    private final Pipe topPipe, bottomPipe;

    public PipeGroup() {
        topPipe = new Pipe(Pipe.PipeDirection.TOP);
        bottomPipe = new Pipe(Pipe.PipeDirection.BOTTOM);

        topPipe.getComponent(PositionComponent.class).getPosition().set(Gdx.graphics.getWidth() - topPipe.getComponent(TextureComponent.class).getTexture().getWidth(), Gdx.graphics.getHeight() - topPipe.getComponent(TextureComponent.class).getTexture().getHeight() * (Gdx.graphics.getHeight() / 1080f));
        bottomPipe.getComponent(PositionComponent.class).getPosition().set(Gdx.graphics.getWidth() - bottomPipe.getComponent(TextureComponent.class).getTexture().getWidth(), 0);
    }

    public void update(Batch batch) {
        topPipe.update(batch);
        bottomPipe.update(batch);
    }

    @Override
    public void dispose() {
        topPipe.dispose();
        bottomPipe.dispose();
    }
}
