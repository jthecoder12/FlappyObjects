package jthecoder12.flappyobjects.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Circle;
import org.jetbrains.annotations.NotNull;

public final class CircleCollider implements Component {
    final Circle circle;
    private final Entity entity;

    public CircleCollider(@NotNull Entity entity) {
        circle = new Circle(entity.getComponent(PositionComponent.class).getPosition(), entity.getComponent(CircleComponent.class).getRadius());
        this.entity = entity;
    }

    public void update() {
        circle.setPosition(entity.getComponent(PositionComponent.class).getPosition());
    }
}
