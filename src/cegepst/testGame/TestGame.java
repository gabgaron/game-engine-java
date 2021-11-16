package cegepst.testGame;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

import java.util.ArrayList;

public class TestGame extends Game {
    private GamePad gamePad;
    private Player player;
    private ArrayList<Blockade> worldBorders;
    private ArrayList<Brick> bricks;

    @Override
    public void initialize() {
        worldBorders = new ArrayList<>();
        bricks = new ArrayList<>();
        Blockade bottomBorder = new Blockade();
        bottomBorder.setDimension(800,20);
        bottomBorder.teleport(0,400);
        bricks.add(new Brick(500, 100));
        bricks.add(new Brick(500, 116));
        bricks.add(new Brick(500, 132));
        bricks.add(new Brick(484, 380));
        bricks.add(new Brick(500, 380));
        worldBorders.add(bottomBorder);
        gamePad = new GamePad();
        player = new Player(gamePad);
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }

        player.update();
        if (gamePad.isJumpedPressed() && player.canJump()) {
            player.jump();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        for (Blockade blockade : worldBorders) {
            blockade.draw(buffer);
        }
        for (Brick brick : bricks) {
            brick.draw(buffer);
        }
        player.draw(buffer);
    }

    @Override
    public void conclude() {

    }
}
