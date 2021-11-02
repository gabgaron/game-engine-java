package cegepst.engine.entities;

import cegepst.engine.controls.Direction;

import java.awt.*;

public abstract class MovableEntity extends UpdatableEntity {

    private Collision collision;
    private int speed;
    private Direction direction = Direction.UP;
    private boolean moved;
    int lastX;
    int lastY;

    public MovableEntity() {
        collision = new Collision(this);
        speed = 1;
    }

    @Override
    public void update() {
        moved = false;
    }

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
        int allowedSpeed = collision.getAllowedSpeed(direction);
        x += direction.getVelocityX(allowedSpeed);
        y += direction.getVelocityY(allowedSpeed);
        moved = (x != lastX || y != lastY);
        lastX = x;
        lastY = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getHitBox() {
        switch (direction) {
            case UP: return getUpperHitBox();
            case DOWN: return getLowerHitBox();
            case LEFT: return  getLeftHitBox();
            case RIGHT: return  getRightHitBox();
            default: return getBounds();
        }
    }

    public boolean hitBoxIntersectsWith(StaticEntity other) {
        return getHitBox().intersects(other.getBounds());
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle(x - speed, y , speed, height);
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + width, y , speed, height);
    }

    private Rectangle getUpperHitBox() {
        return new Rectangle(x, y - speed , width, speed);
    }

    private Rectangle getLowerHitBox() {
        return new Rectangle(x, y + height , width, speed);
    }
}
