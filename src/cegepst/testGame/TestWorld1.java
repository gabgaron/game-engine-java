package cegepst.testGame;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.SpriteSheetLoader;

import java.awt.*;
import java.util.ArrayList;

public class TestWorld1 {

    private static final String MAP_PATH = "images/4.jpg";
    private ArrayList<Blockade> worldBorders;
    private ArrayList<Brick> bricks;
    private ArrayList<Spike> spikes;
    private Image background;

    public TestWorld1(int test) {
        worldBorders = new ArrayList<>();
        bricks = new ArrayList<>();
        spikes = new ArrayList<>();
        generateWorld(test);
        load();
    }

    public void load() {
        background = SpriteSheetLoader.loadSpriteSheet(this, MAP_PATH);
    }

    public void draw(Buffer buffer) {
        buffer.drawImage(background, 0, 0);
        for (Blockade blockade : worldBorders) {
            blockade.draw(buffer);
        }
        for (Brick brick : bricks) {
            brick.draw(buffer);
        }
        for (Spike spike : spikes) {
            spike.draw(buffer);
        }

    }

    public void unregisterCollision() {
        for (Blockade border : worldBorders) {
            CollidableRepository.getInstance().unregisterEntity(border);
        }
        for (Brick brick : bricks) {
            CollidableRepository.getInstance().unregisterEntity(brick);
        }
        for (Spike spike : spikes) {
            CollidableRepository.getInstance().unregisterEntity(spike);
        }
    }

    private void generateWorld(int test) {
        Blockade bottomBorder = new cegepst.testGame.Blockade();
        bottomBorder.setDimension(8000,20);
        bottomBorder.teleport(-1000,580);
        worldBorders.add(bottomBorder);
        generateFirstWorld(test);
        generateSecondWorld(test);
    }

    private void generateFirstWorld(int test) {
        if (test == 0) {

            bricks.add(new Brick(1040, 420));
            bricks.add(new Brick(1110, 480));
            bricks.add(new Brick(1220, 420));
            bricks.add(new Brick(1280, 450));
            bricks.add(new Brick(1410, 390));
            bricks.add(new Brick(1520, 330));
            bricks.add(new Brick(1610, 280));
            bricks.add(new Brick(1642, 280));
            bricks.add(new Brick(1674, 280));
            spikes.add(new Spike(1690, 264));
            bricks.add(new Brick(1780, 340));
            bricks.add(new Brick(1890, 290));
            bricks.add(new Brick(2000, 240));
            bricks.add(new Brick(2032, 240));
            bricks.add(new Brick(2100, 310));
            bricks.add(new Brick(2232, 310));
            bricks.add(new Brick(2376, 380));
            bricks.add(new Brick(2408, 380));
            bricks.add(new Brick(2440, 380));
            bricks.add(new Brick(2472, 380));
            bricks.add(new Brick(2582, 350));
            bricks.add(new Brick(2692, 320));
            bricks.add(new Brick(2802, 290));

            //bricks.add(new Brick(1720, 310));
            //bricks.add(new Brick(1752, 310));

        }
    }

    private void generateSecondWorld(int test) {
        if (test == 1) {
            bricks.add(new Brick(8, 240));
            bricks.add(new Brick(40, 240));
            bricks.add(new Brick(128, 310));
            bricks.add(new Brick(260, 310));
            bricks.add(new Brick(404, 380));
            bricks.add(new Brick(436, 380));
            bricks.add(new Brick(468, 380));
            bricks.add(new Brick(500, 380));
            bricks.add(new Brick(600, 350));
            bricks.add(new Brick(710, 320));
            bricks.add(new Brick(820, 290));
        }
    }
}
