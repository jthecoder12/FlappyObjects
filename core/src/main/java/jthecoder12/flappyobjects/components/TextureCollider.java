package jthecoder12.flappyobjects.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import org.jetbrains.annotations.NotNull;

public final class TextureCollider implements Component {
    private final Sprite sprite;
    private final Entity entity;

    public TextureCollider(@NotNull Entity entity) {
        sprite = new Sprite(entity.getComponent(TextureComponent.class).getTexture());

        this.entity = entity;
        update();
    }

    public void update() {
        sprite.setPosition(entity.getComponent(PositionComponent.class).getPosition().x, entity.getComponent(PositionComponent.class).getPosition().y);
    }

    public boolean checkWithCircle(@NotNull CircleCollider circleCollider) {
        return Intersector.overlaps(circleCollider.circle, sprite.getBoundingRectangle());
    }
}
