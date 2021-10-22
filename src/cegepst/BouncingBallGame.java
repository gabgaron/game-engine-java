package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.GameTime;

import java.awt.*;

public class BouncingBallGame extends Game {
    private int score = 0;
    private Ball ball;



    @Override
    public void initialize() {
        ball = new Ball(20);
    }

    @Override
    public void update() {
        ball.update();
        if (ball.hasTouchedBound()) {
            score += 10;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        ball.draw(buffer);
        buffer.drawText("score : " + score, 10,20, Color.WHITE);
        buffer.drawText("FPS : " + GameTime.getCurrentFps(), 10, 40, Color.WHITE);
        buffer.drawText(GameTime.getElapsedFormattedTime(), 10, 70, Color.WHITE);
    }

    @Override
    public void conclude() {

    }
}
