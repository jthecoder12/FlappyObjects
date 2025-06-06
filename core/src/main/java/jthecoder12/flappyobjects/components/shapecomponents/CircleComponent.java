package jthecoder12.flappyobjects.components.shapecomponents;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import jthecoder12.flappyobjects.components.PositionComponent;
import jthecoder12.flappyobjects.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

public final class CircleComponent implements ShapeComponent {
    private final Vector2 position;
    private final float radius;
    private final Color color;

    public CircleComponent(@NotNull Entity entity, float radius, Color color) {
        position = entity.getComponent(PositionComponent.class).getPosition();
        this.radius = radius;
        this.color = color;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void render() {
        GameScreen.INSTANCE.shapeRenderer.setColor(color);
        GameScreen.INSTANCE.shapeRenderer.circle(position.x, position.y, radius);
    }
}
