package cegepst.engine.entities;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MovableEntity extends UpdatableEntity {

    private Collision collision;
    private int speed;
    private int horizontalSpeed;
    private Direction direction = Direction.UP;
    private boolean moved;
    private int lastX;
    private int lastY;

    public MovableEntity() {
        collision = new Collision(this);
        speed = 1;
        horizontalSpeed = 1;
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

    public int getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHorizontalSpeed(int horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void drawHitBox(Buffer buffer) {
        Rectangle rectangle = getHitBox();
        Color color = new Color(255, 0, 0, 200);
        buffer.drawRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, color);
    }

    public Rectangle getHitBox() {
        switch (direction) {
            case UP: return getUpperHitBox();
            case DOWN: return getLowerHitBox();
            case LEFT: return  getLeftHitBox();
            case RIGHT: return  getRightHitBox();
            case JUMPING: return getJumpingHitBox();
            case FALLING: return getFallingHitBox();
            default: return getBounds();
        }
    }

    public boolean hitBoxIntersectsWith(StaticEntity other) {
        return getHitBox().intersects(other.getBounds());
    }

    public Image[] assignImages(int startingX, int startingY, int tableLength, BufferedImage spriteSheet) {
        Image[] images = new Image[tableLength];
        int x = startingX;
        int y = startingY;
        for (int i = 0; i < tableLength; i++) {
            images[i] = spriteSheet.getSubimage(x,y,width,height);
            x += width;
        }
        return images;
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle(x - horizontalSpeed, y , horizontalSpeed, height);
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + width, y ,1 , height);
    }

    private Rectangle getUpperHitBox() {
        return new Rectangle(x, y - speed , width, speed);
    }

    private Rectangle getJumpingHitBox() {
        return new Rectangle(x, height , width, -1);
    }

    private Rectangle getLowerHitBox() {
        return new Rectangle(x, y + height , width, speed);
    }

    private Rectangle getFallingHitBox() {
        return new Rectangle(x, y + height , width, 1);
    }
}
