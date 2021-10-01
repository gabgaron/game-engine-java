package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameWindow extends JFrame {

    private static final int SLEEP = 25;
    private Random rnd = new Random();
    private boolean playing = true;
    private int radius = 25;
    private int x = getRandomNumber(0 + radius * 2, 800 - radius * 2);
    private int y = getRandomNumber(0 + radius * 2, 600 - radius * 2);
    private int dx = getRandomNumber(0,1) == 0 ? 5 : -5;
    private int dy = getRandomNumber(0,1) == 0 ? 5 : -5;
    private JPanel panel;
    private BufferedImage bufferedImage;
    private Graphics2D buffer;
    private long before;
    private int score = 0;

    public GameWindow() {
        setSize(800,600);
        setLocationRelativeTo(null); // center frame on screen
        setResizable(false);
        setTitle("Bouncing Balls");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close the app when click on the "X"
        setState(JFrame.NORMAL);

        // Supprimer la barre de l'application
        // setUndecorated(true);
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        add(panel); // ajouter la panneau dans le jframe

    }

    public void start() {
        setVisible(true);
        before = System.currentTimeMillis();
        while(playing) {
            bufferedImage = new BufferedImage(800,600, BufferedImage.TYPE_INT_RGB);
            buffer = bufferedImage.createGraphics();
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            buffer.setRenderingHints(rh);

            update();
            drawOnBuffer();
            drawBufferOnScreen();

            long sleep = SLEEP - System.currentTimeMillis() - before;
            if (sleep < 0) {
                sleep = 4;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            before = System.currentTimeMillis();

        }
    }

    public void update() {
        x += dx;
        y += dy;
        if (y <= radius || y >= 600 - radius) {
            dy *= -1;
            score += 10;
        }
        if (x <= radius || x >= 800 - radius) {
            dx *= -1;
            score += 10;
        }
    }

    public void drawOnBuffer() {
        buffer.setPaint(Color.RED);
        buffer.fillOval(x, y, radius * 2, radius * 2);
        buffer.setPaint(Color.WHITE);
        buffer.drawString("Score " + score, 10, 20);
    }

    public void drawBufferOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }

    private int getRandomNumber(int min, int max) {
        return  rnd.nextInt((max - min) + 1) + min;
    }

}
