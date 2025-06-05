package jthecoder12.flappyobjects.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.jetbrains.annotations.NotNull;

public final class PlayerPhysicsComponent implements Component {
    private final Entity entity;
    private final Body body;

    public PlayerPhysicsComponent(@NotNull Entity entity, @NotNull World world) {
        this.entity = entity;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(entity.getComponent(PositionComponent.class).getPosition());

        body = world.createBody(bodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(entity.getComponent(CircleComponent.class).getRadius());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);

        circleShape.dispose();
    }

    public void update() {
        entity.getComponent(PositionComponent.class).getPosition().set(body.getPosition());
    }

    public void applyForce() {
        body.applyForce(new Vector2(0, 1000000000), entity.getComponent(PositionComponent.class).getPosition(), true);
    }
}
