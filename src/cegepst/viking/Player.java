package cegepst.viking;

import cegepst.engine.Buffer;
import cegepst.engine.SpriteSheetLoader;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends ControllableEntity {

    private static final int ANIMATION_SPEED = 8;
    private int currentAnimationFrame = 1; // idle frame (middle)
    //todo spriteSheet loader;
    //private static final String SPRITE_SHEET_PATH = "images/player.png";
    //private BufferedImage spriteSheet; ai-je le droit de faire ça ?
    private BufferedImage spriteSheet = SpriteSheetLoader.loadSpriteSheet(this,"images/player.png");
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;
    private int nextFrame = ANIMATION_SPEED;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        loadSpriteSheet();
        loadAnimationFrames();

    }

    @Override
    public void update() {
        super.update();
        moveAccordingToController();
        if (hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= leftFrames.length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1; // back to idle frame
        }
    }

    @Override
    public void draw(Buffer buffer) {
        //todo si dans le jeu, criss de cave. va refactor ça...
        if (getDirection() == Direction.RIGHT) {
            buffer.drawImage(rightFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.LEFT) {
            buffer.drawImage(leftFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.UP) {
            buffer.drawImage(upFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.DOWN) {
            buffer.drawImage(downFrames[currentAnimationFrame], x, y);
        }
    }

    private void loadSpriteSheet() {
        //spriteSheet = SpriteSheetLoader.loadSpriteSheet(this, SPRITE_SHEET_PATH);
    }
    //todo créer algo pour automatiser ça
    private void loadAnimationFrames() {

        downFrames = new Image[3];
        downFrames[0] = spriteSheet.getSubimage(0, 128, width, height); // 4 x 32 pour aller chercher le viking
        downFrames[1] = spriteSheet.getSubimage(32, 128, width, height);
        downFrames[2] = spriteSheet.getSubimage(64, 128, width, height);

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(0, 160, width, height);
        leftFrames[1] = spriteSheet.getSubimage(32, 160, width, height);
        leftFrames[2] = spriteSheet.getSubimage(64, 160, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(0, 192, width, height);
        rightFrames[1] = spriteSheet.getSubimage(32, 192, width, height);
        rightFrames[2] = spriteSheet.getSubimage(64, 192, width, height);

        upFrames = new Image[3];
        upFrames[0] = spriteSheet.getSubimage(0, 224, width, height);
        upFrames[1] = spriteSheet.getSubimage(32, 224, width, height);
        upFrames[2] = spriteSheet.getSubimage(64, 224, width, height);
    }
}

