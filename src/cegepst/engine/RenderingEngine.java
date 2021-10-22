package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderingEngine {

    private JFrame frame;
    private JPanel panel;
    private BufferedImage bufferedImage;

    public RenderingEngine() {
        initializeFrame();
        initializePanel();
    }

    public void start() {
        frame.setVisible(true);
    }

    public void stop() {
        frame.setVisible(false);
        frame.dispose();
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

    private void initializeFrame() {
        frame = new JFrame();
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null); // center frame on screen
        frame.setResizable(false);
        frame.setTitle("Bouncing Balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close the app when click on the "X"
        frame.setState(JFrame.NORMAL);
        // Supprimer la barre de l'application
        // setUndecorated(true);
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        frame.add(panel); // ajouter la panneau dans le jframe
    }

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }
}
