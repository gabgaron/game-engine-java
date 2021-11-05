package cegepst.viking;

import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_Q;
    private int soundKey = KeyEvent.VK_SPACE;

    public GamePad() {
        bindKey(quitKey);
        bindKey(soundKey);
        RenderingEngine.getInstance().addKeyListener(this);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }

    public boolean isSoundPressed() {
        return isKeyPressed(soundKey);
    }
}
