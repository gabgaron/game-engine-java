package cegepst.viking;

import cegepst.engine.Buffer;
import cegepst.engine.Camera;
import cegepst.engine.Game;
import cegepst.engine.RenderingEngine;

public class VikingGame extends Game {
    private World world;
    private GamePad gamePad;
    private Player player;
    private Tree tree;
    private int soundCooldown;
    private MusicLoader music;


    @Override
    public void initialize() {
        gamePad = new GamePad();
        world = new World();
        world.load();
        tree = new Tree(300, 350);
        player = new Player(gamePad);
        player.teleport(200, 200);
        music = new MusicLoader("musics/map1.wav");
        RenderingEngine.getInstance().getScreen().hideCursor();
        RenderingEngine.getInstance().getScreen().fullScreen();
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (player.getY() < tree.getY() +52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }
        player.update();

        soundCooldown--;
        if (soundCooldown < 0) {
            soundCooldown = 0;
        }
        if (gamePad.isSoundPressed() && soundCooldown == 0) {
            soundCooldown = 40;
            Sound.play("sounds/best1.wav");
        }
    }

    @Override
    public void draw(Buffer buffer) {
        world.draw(buffer);
        if (player.getY() < tree.getY() +52) { // 52 = 80 - 28
            player.draw(buffer);
            tree.draw(buffer);
        } else {
            tree.draw(buffer);
            player.draw(buffer);
        }
    }

    @Override
    public void conclude() {

    }
}
