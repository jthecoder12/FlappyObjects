package jthecoder12.flappyobjects.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.jetbrains.annotations.NotNull;

public final class SpriteComponent implements Component {
    private final Texture texture;
    private final Sprite sprite;
    private final Entity entity;

    public SpriteComponent(@NotNull Entity entity, Texture texture, float height) {
        this.entity = entity;

        this.texture = texture;
        sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth(), texture.getHeight() * (Gdx.graphics.getHeight() / 1080f) * height);
    }

    public void draw(@NotNull Batch batch) {
        sprite.setPosition(entity.getComponent(PositionComponent.class).getPosition().x, entity.getComponent(PositionComponent.class).getPosition().y);
        sprite.draw(batch);
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
