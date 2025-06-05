package jthecoder12.flappyobjects.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import org.jetbrains.annotations.NotNull;

public final class TextureComponent implements Component {
    private final Entity entity;
    private final Texture texture;

    public TextureComponent(@NotNull Entity entity, Texture texture) {
        this.entity = entity;
        this.texture = texture;
    }

    public void draw(@NotNull Batch batch, float height) {
        batch.draw(texture, entity.getComponent(PositionComponent.class).getPosition().x, entity.getComponent(PositionComponent.class).getPosition().y, texture.getWidth(), texture.getHeight() * (Gdx.graphics.getHeight() / 1080f) * height);
    }

    public Texture getTexture() {
        return texture;
    }
}
