package cegepst.MovingRectangle;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MovingRectangleGame extends Game {
    private GamePad controllerOne;
    private GamePad controllerTwo;
    private Player playerOne;
    private Player playerTwo;
    private ArrayList<FootPrint> footPrints;


    @Override
    public void initialize() {
        controllerOne = new GamePad();
        controllerTwo = new GamePad();
        controllerTwo.setUpKey(KeyEvent.VK_W);
        controllerTwo.setDownKey(KeyEvent.VK_S);
        controllerTwo.setLeftKey(KeyEvent.VK_A);
        controllerTwo.setRightKey(KeyEvent.VK_D);
        playerOne = new Player(controllerOne, Color.pink);
        playerTwo = new Player(controllerTwo, Color.green);
        footPrints = new ArrayList<>();
        addKeyListener(controllerOne);
        addKeyListener(controllerTwo);
    }

    @Override
    public void update() {
        if (controllerOne.isQuitPressed()) {
            stop();
        }
        playerOne.update();
        playerTwo.update();
        if (controllerOne.isMoving()) {
            footPrints.add(playerOne.layFootPrint());
        }
        if (controllerTwo.isMoving()) {
            footPrints.add(playerTwo.layFootPrint());
        }
    }

    @Override
    public void draw(Buffer buffer) {
        for (FootPrint footPrint : footPrints) {
            footPrint.draw(buffer);
        }
        playerOne.draw(buffer);
        playerTwo.draw(buffer);

    }

    @Override
    public void conclude() {

    }
}
