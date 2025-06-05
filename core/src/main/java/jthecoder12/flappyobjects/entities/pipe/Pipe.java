package jthecoder12.flappyobjects.entities.pipe;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import jthecoder12.flappyobjects.components.CircleCollider;
import jthecoder12.flappyobjects.components.PositionComponent;
import jthecoder12.flappyobjects.components.SpriteCollider;
import jthecoder12.flappyobjects.components.SpriteComponent;
import jthecoder12.flappyobjects.screens.GameScreen;

public final class Pipe extends Entity implements Disposable {
    private final Texture pipeTexture;
    private final float height;
    private final Sound hitSound;

    enum PipeDirection {
        TOP,
        BOTTOM
    }

    Pipe(PipeDirection pipe, float height) {
        add(new PositionComponent());

        if(pipe == PipeDirection.TOP) pipeTexture = new Texture("textures/pipes/top.png");
        // Bottom pipe
        else pipeTexture = new Texture("textures/pipes/bottom.png");
        this.height = height;
        add(new SpriteComponent(this, pipeTexture, height));
        add(new SpriteCollider(this));

        hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Hit.wav"));
    }

    void update(Batch batch) {
        getComponent(PositionComponent.class).getPosition().sub(2.25f, 0);
        System.out.println(height);

        getComponent(SpriteComponent.class).draw(batch);

        if(getComponent(SpriteCollider.class).checkWithCircle(GameScreen.INSTANCE.player.getComponent(CircleCollider.class))) {
            hitSound.play();
            GameScreen.INSTANCE.running = false;
        }
    }

    @Override
    public void dispose() {
        hitSound.dispose();
        pipeTexture.dispose();
    }
}
