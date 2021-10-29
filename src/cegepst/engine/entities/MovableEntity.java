package cegepst.engine.entities;

import cegepst.engine.controls.Direction;

public abstract class MovableEntity extends UpdatableEntity {

    private int speed;
    private Direction direction = Direction.UP;

    public void moveDown() {
        move(Direction.DOWN);
    }

    public void moveUp() {
        move(Direction.UP);
    }

    public void moveLeft() {
        move(Direction.LEFT);
    }

    public void moveRight() {
        move(Direction.RIGHT);
    }

    public void move(Direction direction) {
        this.direction = direction;
        x += direction.getVelocityX(speed);
        y += direction.getVelocityY(speed);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
