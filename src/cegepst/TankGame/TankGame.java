package cegepst.TankGame;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

import java.util.ArrayList;

public class TankGame extends Game {

    private GamePad gamePad;
    private Tank tank;
    private ArrayList<Missile> missiles;
    private ArrayList<Brick> bricks;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        tank = new Tank(gamePad);
        missiles = new ArrayList<>();
        bricks = new ArrayList<>();
        bricks.add(new Brick(500, 100));
        bricks.add(new Brick(500, 116));
        bricks.add(new Brick(500, 132));
        bricks.add(new Brick(484, 148));
        bricks.add(new Brick(500, 164));
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }

        tank.update();
        if (gamePad.isFirePressed() && tank.canFire()) {
            missiles.add(tank.fire());
        }
        for (Missile missile : missiles) {
            missile.update();
        }

    }

    @Override
    public void draw(Buffer buffer) {
        for (Missile missile : missiles) {
            missile.draw(buffer);
        }
        for (Brick brick: bricks) {
            brick.draw(buffer);
        }
        tank.draw(buffer);
    }

    @Override
    public void conclude() {

    }
}