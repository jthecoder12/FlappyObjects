package jthecoder12.flappyobjects.entities.pipe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import jthecoder12.flappyobjects.components.PositionComponent;
import jthecoder12.flappyobjects.components.TextureComponent;
import jthecoder12.flappyobjects.screens.GameScreen;

public final class PipeGroup implements Disposable {
    private final Pipe topPipe, bottomPipe;
    private final Sound coinSound;
    private final float differenceBy;
    private boolean gotPoint = false;
    private boolean requireSpawnNew = true;

    public PipeGroup() {
        topPipe = new Pipe(Pipe.PipeDirection.TOP);
        bottomPipe = new Pipe(Pipe.PipeDirection.BOTTOM);

        topPipe.getComponent(PositionComponent.class).getPosition().set(Gdx.graphics.getWidth() - topPipe.getComponent(TextureComponent.class).getTexture().getWidth(), Gdx.graphics.getHeight() - topPipe.getComponent(TextureComponent.class).getTexture().getHeight() * (Gdx.graphics.getHeight() / 1080f));
        bottomPipe.getComponent(PositionComponent.class).getPosition().set(Gdx.graphics.getWidth() - bottomPipe.getComponent(TextureComponent.class).getTexture().getWidth(), 0);

        coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin8.wav"));

        differenceBy = MathUtils.random(1, 2f);
        System.out.println(differenceBy);
    }

    public void update(Batch batch) {
        topPipe.height = differenceBy;
        bottomPipe.height = Gdx.graphics.getHeight() - differenceBy - Gdx.graphics.getHeight() + Gdx.graphics.getHeight() / (Gdx.graphics.getHeight() / 2.35f);

        topPipe.update(batch);
        bottomPipe.update(batch);

        if(topPipe.getComponent(PositionComponent.class).getPosition().x < Gdx.graphics.getWidth() / 4.8f && !gotPoint) {
            gotPoint = true;
            GameScreen.INSTANCE.score += 1;
            GameScreen.INSTANCE.scoreLabel.setText("Score: " + GameScreen.INSTANCE.score);
            System.out.println(GameScreen.INSTANCE.score);
            coinSound.play(0.3f);
        }

        if(getX() < Gdx.graphics.getWidth() / 2f && requireSpawnNew) {
            requireSpawnNew = false;
            GameScreen.INSTANCE.spawnNewPipe();
        }
    }

    public float getX() {
        return topPipe.getComponent(PositionComponent.class).getPosition().x;
    }

    @Override
    public void dispose() {
        coinSound.dispose();
        topPipe.dispose();
        bottomPipe.dispose();
    }
}
