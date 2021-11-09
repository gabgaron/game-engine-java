package cegepst.engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheetLoader {

    public static BufferedImage loadSpriteSheet(Object entity, String path) {
        try {
            return ImageIO.read(entity.getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
