package jthecoder12.flappyobjects.components.colliders;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import jthecoder12.flappyobjects.components.shapecomponents.SpriteComponent;
import jthecoder12.flappyobjects.entities.pipe.Pipe;
import org.jetbrains.annotations.NotNull;

public final class SpriteCollider implements Component {
    private final Sprite sprite;

    public SpriteCollider(@NotNull Pipe entity) {
        sprite = entity.getComponent(SpriteComponent.class).getSprite();
    }

    public boolean checkWithCircle(@NotNull CircleCollider circleCollider) {
        return Intersector.overlaps(circleCollider.circle, sprite.getBoundingRectangle());
    }
}
