package jthecoder12.flappyobjects.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public final class PositionComponent implements Component {
    private final Vector2 position;

    public PositionComponent() {
        position = new Vector2();
    }

    public Vector2 getPosition() {
        return position;
    }
}
