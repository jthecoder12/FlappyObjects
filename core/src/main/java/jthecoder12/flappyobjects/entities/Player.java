package jthecoder12.flappyobjects.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import jthecoder12.flappyobjects.components.CircleComponent;
import jthecoder12.flappyobjects.components.PositionComponent;

public final class Player extends Entity {
    public Player() {
        add(new PositionComponent());
        add(new CircleComponent(this, 15, Color.YELLOW));
    }
}
