package cegepst.testGame;

import cegepst.engine.Buffer;
import cegepst.engine.Camera;
import cegepst.engine.Game;
import cegepst.engine.RenderingEngine;


public class TestGame extends Game {
    private GamePad gamePad;
    private Player player;
    private TestWorld1 testWorld1;
    private int worldCounter;

    @Override
    public void initialize() {
        testWorld1 = new TestWorld1(worldCounter);
        gamePad = new GamePad();
        player = new Player(gamePad);
        //RenderingEngine.getInstance().getScreen().fullScreen();

    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (gamePad.isJumpedPressed() && player.canJump()) {
            player.jump();
        }
        player.update();
        setCamera();
        generateNewWorld();
        checkIfPlayerIsDead();
    }



    @Override
    public void draw(Buffer buffer) {
        testWorld1.draw(buffer);
        player.draw(buffer);
    }

    @Override
    public void conclude() {

    }

    private void generateNewWorld() {
        if (player.getX() > 2250) {

            worldCounter++;
            testWorld1.unregisterCollision();
            testWorld1 = new TestWorld1(worldCounter);
            Camera.getInstance().setX(0);
            player.setX(250);
        }
    }

    private void setCamera() {

        Camera.getInstance().position(player);

    }

    private void checkIfPlayerIsDead() {
        if (player.hasDied()) {
            worldCounter = 0;
            testWorld1.unregisterCollision();
            testWorld1 = new TestWorld1(worldCounter);
            Camera.getInstance().setX(0);
            player.setX(250);
        }
    }
}
