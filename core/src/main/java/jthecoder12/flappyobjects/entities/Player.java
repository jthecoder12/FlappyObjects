package jthecoder12.flappyobjects.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import jthecoder12.flappyobjects.components.colliders.CircleCollider;
import jthecoder12.flappyobjects.components.shapecomponents.CircleComponent;
import jthecoder12.flappyobjects.components.PlayerPhysicsComponent;
import jthecoder12.flappyobjects.components.PositionComponent;
import jthecoder12.flappyobjects.screens.GameScreen;

public final class Player extends Entity implements Disposable {
    private final Sound jumpSound, hitSound;

    public Player(World world) {
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/SFX_Jump_09.wav"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Hit.wav"));

        PositionComponent positionComponent = new PositionComponent();
        positionComponent.getPosition().set(Gdx.graphics.getWidth() / 4.8f, Gdx.graphics.getHeight() / 2.7f);
        add(positionComponent);
        add(new CircleComponent(this, 15, Color.YELLOW));
        add(new CircleCollider(this));
        add(new PlayerPhysicsComponent(this, world));
    }

    public void update() {
        getComponent(CircleComponent.class).render();
        getComponent(CircleCollider.class).update();
        getComponent(PlayerPhysicsComponent.class).update();

        if(Gdx.input.justTouched() && GameScreen.INSTANCE.running) {
            jumpSound.play();
            getComponent(PlayerPhysicsComponent.class).applyForce();
        }

        float yPos = getComponent(PositionComponent.class).getPosition().y;
        if((yPos >= Gdx.graphics.getHeight() || yPos <= 0) && GameScreen.INSTANCE.running && MathUtils.randomBoolean()) {
            hitSound.play();
            GameScreen.INSTANCE.running = false;
        }
    }

    @Override
    public void dispose() {
        jumpSound.dispose();
        hitSound.dispose();
    }
}
