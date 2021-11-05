package cegepst.viking;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class VikingGame extends Game {
    private World world;
    private GamePad gamePad;
    private Player player;
    private Tree tree;
    private int soundCooldown;


    @Override
    public void initialize() {
        gamePad = new GamePad();
        world = new World();
        world.load();
        tree = new Tree(300, 350);
        player = new Player(gamePad);
        player.teleport(200, 200);



        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("musics/map1.wav"));
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
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
