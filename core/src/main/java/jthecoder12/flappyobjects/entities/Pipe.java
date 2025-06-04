package jthecoder12.flappyobjects.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import jthecoder12.flappyobjects.components.CircleCollider;
import jthecoder12.flappyobjects.components.PositionComponent;
import jthecoder12.flappyobjects.components.TextureCollider;
import jthecoder12.flappyobjects.components.TextureComponent;
import jthecoder12.flappyobjects.screens.GameScreen;

final class Pipe extends Entity implements Disposable {
    private final Texture pipeTexture;

    enum PipeDirection {
        TOP,
        BOTTOM
    }

    Pipe(PipeDirection pipe) {
        add(new PositionComponent());

        if(pipe == PipeDirection.TOP) pipeTexture = new Texture("textures/pipes/top.png");
        // Bottom pipe
        else pipeTexture = new Texture("textures/pipes/bottom.png");
        add(new TextureComponent(this, pipeTexture));
        add(new TextureCollider(this));
    }

    void update(Batch batch) {
        getComponent(PositionComponent.class).getPosition().sub(2.25f, 0);

        getComponent(TextureCollider.class).update();
        getComponent(TextureComponent.class).draw(batch);

        if(getComponent(TextureCollider.class).checkWithCircle(GameScreen.INSTANCE.player.getComponent(CircleCollider.class))) System.out.println("You lose");
    }

    @Override
    public void dispose() {
        pipeTexture.dispose();
    }
}
