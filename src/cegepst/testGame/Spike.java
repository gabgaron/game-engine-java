package cegepst.testGame;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.SpriteSheetLoader;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Spike extends StaticEntity {

    private Image image;
    private static final String SPIKE_PATH = "images/spike.png";

    public Spike(int x, int y) {
        teleport(x, y);
        setDimension(16, 16);
        image = SpriteSheetLoader.loadSpriteSheet(this, SPIKE_PATH);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, getX(), getY());
    }
}
