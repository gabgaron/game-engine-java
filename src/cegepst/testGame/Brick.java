package cegepst.testGame;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.SpriteSheetLoader;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Brick extends StaticEntity {

    private Image image;
    private static final String BRICK_PATH = "images/brick.png";

    public Brick(int x, int y) {
        teleport(x, y);
        setDimension(32, 20);
        image = SpriteSheetLoader.loadSpriteSheet(this, BRICK_PATH);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, getX(), getY());
    }
}
