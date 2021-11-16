package cegepst.testGame;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Player extends ControllableEntity {

    private int momentum;
    private boolean hasJumped = false;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        teleport(0, 368);
        setHorizontalSpeed(6);
    }

    public void jump() {
        hasJumped = true;
        momentum = 15;
    }

    public boolean canJump() {
        setDirection(Direction.FALLING);
        for (StaticEntity other : CollidableRepository.getInstance()) {
            if (hitBoxIntersectsWith(other)) {
                return true;
            }
        }
        return false;
    }

    public void fall() {
        if (!hasJumped) {
            setSpeed(momentum);
            moveDown();
            setDirection(Direction.FALLING);
        }
    }

    @Override
    public void update() {
        moveAccordingToController();
        if (momentum > 0 && hasJumped) {
            setSpeed(momentum);
            moveUp();
        }
        if (momentum == 0) {
            hasJumped = false;
        }
        if (hasJumped) {
            System.out.println(getSpeed());
            momentum--;
        } else {
            momentum++;
        }
        fall();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y,width, height, Color.BLUE);
        if (hasMoved()) {
            drawHitBox(buffer);
        }
    }
}
