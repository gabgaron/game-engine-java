package cegepst.viking;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

public class VikingGame extends Game {

    private GamePad gamePad;
    @Override
    public void initialize() {
        gamePad = new GamePad();
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void conclude() {

    }
}
