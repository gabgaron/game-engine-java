package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class RenderingEngine {
    private static RenderingEngine instance;
    private Screen screen;
    private JPanel panel;
    private BufferedImage bufferedImage;

    public static RenderingEngine getInstance() {
        if (instance == null) {
            instance = new RenderingEngine();
        }
        return instance;
    }

    private RenderingEngine() {
        initializeScreen();
        initializePanel();
    }

    public void start() {
        screen.start();
    }

    public void stop() {
        screen.end();
    }

    public void addKeyListener(KeyListener listener) {
        panel.addKeyListener(listener);
    }

    public void renderBufferOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }

    public Buffer getRenderingBuffer() {
        //faire un viewport pour changer les dimensions
        bufferedImage = new BufferedImage(800,600, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHints(getOptimalRenderingHints());
        return new Buffer(graphics);
    }

    public Screen getScreen() {
        return screen;
    }

    private void initializeScreen() {
        screen = new Screen();
        screen.setTitle("game");
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        screen.setPanel(panel);
    }

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }
}
