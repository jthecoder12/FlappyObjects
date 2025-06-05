package jthecoder12.flappyobjects.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import jthecoder12.flappyobjects.entities.pipe.Pipe;
import org.jetbrains.annotations.NotNull;

public final class TextureCollider implements Component {
    private final Sprite sprite;
    private final Entity entity;

    public TextureCollider(@NotNull Pipe entity) {
        sprite = new Sprite(entity.getComponent(TextureComponent.class).getTexture());
        sprite.setSize(entity.getComponent(TextureComponent.class).getTexture().getWidth(), entity.getHeight());

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
