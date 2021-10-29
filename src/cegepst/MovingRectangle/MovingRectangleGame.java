package cegepst.MovingRectangle;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

import java.awt.*;
import java.util.ArrayList;

public class MovingRectangleGame extends Game {
    private GamePad controllerOne;
    private Player playerOne;
    private ArrayList<FootPrint> footPrints;


    @Override
    public void initialize() {
        controllerOne = new GamePad();
        playerOne = new Player(controllerOne, Color.pink);
        footPrints = new ArrayList<>();
        addKeyListener(controllerOne);
    }

    @Override
    public void update() {
        if (controllerOne.isQuitPressed()) {
            stop();
        }
        playerOne.update();
        if (controllerOne.isMoving()) {
            footPrints.add(playerOne.layFootPrint());
        }
    }

    @Override
    public void draw(Buffer buffer) {
        for (FootPrint footPrint : footPrints) {
            footPrint.draw(buffer);
        }
        playerOne.draw(buffer);

    }

    @Override
    public void conclude() {

    }
}
