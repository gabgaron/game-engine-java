package cegepst.testGame;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.SpriteSheetLoader;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends ControllableEntity {

    private static final int ANIMATION_SPEED = 8;
    private int currentAnimationFrame = 1;
    private BufferedImage spriteSheet = SpriteSheetLoader.loadSpriteSheet(this,"images/hojikasmonsters.png");
    private Image[] rightFrames;
    private int nextFrame = ANIMATION_SPEED;
    private int lastY;
    private int momentum;
    private boolean hasJumped = false;
    private boolean isFalling;
    private boolean dead;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        teleport(300, 548);
        setHorizontalSpeed(4);
        loadAnimationFrames();
    }

    @Override
    public void update() {
        dead = false;
        moveRight();
        keepMovingUp();
        fall();
        lastY = y;
        updateMomentum();
        updateFrames();
        checkHitBox();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(rightFrames[currentAnimationFrame], x, y);
        drawHitBox(buffer);
    }

    public void jump() {
        hasJumped = true;
        momentum = 14;
    }

    public boolean canJump() {
        setDirection(Direction.FALLING);
        for (StaticEntity entity : CollidableRepository.getInstance()) {
            if (hitBoxIntersectsWith(entity)) {
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
            isFalling = getY() > lastY;
        }
    }

    public boolean hasDied() {
        return dead;
    }

    private void keepMovingUp() {
        if (momentum > 0 && hasJumped) {
            setSpeed(momentum);
            moveUp();
            checkHitBox();
        }
        if (momentum == 1) {
            hasJumped = false;
        }
    }

    private void checkHitBox() {
        for (StaticEntity entity : CollidableRepository.getInstance().getRegisteredEntities()) {
            if (entity instanceof Brick) {
                setDirection(Direction.RIGHT);
                if (hitBoxIntersectsWith(entity)) {
                    dead = true;
                }
                setDirection(Direction.UP); {
                    if (hitBoxIntersectsWith(entity)) {
                        dead = true;
                    }
                }
            }

            if (entity instanceof Spike) {
                setDirection(Direction.FALLING);
                if (hitBoxIntersectsWith(entity)) {
                    dead = true;
                }
                setDirection(Direction.RIGHT);
                if (hitBoxIntersectsWith(entity)) {
                    dead = true;
                }
            }
        }

    }

    private void updateMomentum() {
        if (!isFalling && !hasJumped) {
            momentum = 0;
        }
        if (hasJumped) {
            momentum--;
        } else {
            momentum++;
        }
    }

    /*public int getMomentum() {
        return momentum;
    }

    public int getLastY() {
        return lastY;
    }*/

    private void loadAnimationFrames() {
        rightFrames = assignImages(0, 192, 3, spriteSheet);
    }

    private void updateFrames() {
        --nextFrame;
        if (nextFrame == 0) {
            ++currentAnimationFrame;
            if (currentAnimationFrame >= rightFrames.length) {
                currentAnimationFrame = 0;
            }
            nextFrame = ANIMATION_SPEED;
        }
    }



}
