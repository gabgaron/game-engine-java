package cegepst.TankGame;

import cegepst.engine.Buffer;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;

import java.awt.*;

public class Tank extends ControllableEntity {

    private int cooldown = 0;

    public Tank(MovementController controller) {
        super(controller);
        setDimension(70, 30);
        setSpeed(2);
        teleport(100, 100);
    }

    public Missile fire() {
        cooldown = 50;
        return new Missile(this);
    }

    public boolean canFire() {
        return cooldown == 0;
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToController();
        cooldown --;
        if (cooldown <= 0) {
            cooldown = 0;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, Color.green);
        if (hasMoved()) {
            drawHitBox(buffer);
        }
    }


}
