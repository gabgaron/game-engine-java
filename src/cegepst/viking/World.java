package cegepst.viking;

import cegepst.engine.Buffer;
import cegepst.engine.SpriteSheetLoader;
import java.awt.*;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH = "images/demo2.png";
    private Image background;
    private ArrayList<Blockade> worldBorders;

    public World() {
        worldBorders = new ArrayList<>();
        Blockade topBorder = new Blockade();
        topBorder.teleport(0, 0);
        topBorder.setDimension(400, 48);
        Blockade topBorder2 = new Blockade();
        topBorder2.setDimension(400, 48);
        topBorder2.teleport(464, 0);
        worldBorders.add(topBorder);
        worldBorders.add(topBorder2);
    }

    public void draw(Buffer buffer) {
        buffer.drawImage(background, 0, -64);
        for (Blockade blockade : worldBorders) {
            blockade.draw(buffer);
        }
    }

    public void load() {
        background = SpriteSheetLoader.loadSpriteSheet(this, MAP_PATH);
    }
}
