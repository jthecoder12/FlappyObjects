package jthecoder12.flappyobjects.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;
import jthecoder12.flappyobjects.components.CircleComponent;
import jthecoder12.flappyobjects.components.PlayerPhysicsComponent;
import jthecoder12.flappyobjects.components.PositionComponent;

public final class Player extends Entity {
    public Player(World world) {
        PositionComponent positionComponent = new PositionComponent();
        positionComponent.getPosition().set(400, 400);
        add(positionComponent);
        add(new CircleComponent(this, 15, Color.YELLOW));
        add(new PlayerPhysicsComponent(this, world));
    }

    public void update() {
        getComponent(CircleComponent.class).render();
        getComponent(PlayerPhysicsComponent.class).update();

        if(Gdx.input.justTouched()) getComponent(PlayerPhysicsComponent.class).applyForce();
    }
}
