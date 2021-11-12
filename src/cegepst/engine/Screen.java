package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen {

    private static GraphicsDevice device;
    private JFrame frame;
    private DisplayMode fullScreenDisplayMode;
    private DisplayMode windowedDisplayMode;
    private boolean isFullScreenMode;
    private Cursor invisibleCursor;

    public Screen() {
        initializeFrame();
        initializeHiddenCursor();
        initializeDevice();
    }

    protected void setPanel(JPanel panel) {
        frame.add(panel);
    }

    protected void setTitle (String title) {
        frame.setTitle(title);
    }

    protected void start() {
        frame.setVisible(true);
    }

    protected void end() {
        frame.setVisible(false);
        frame.dispose();
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null); // center frame on screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close the app when click on the "X"
        frame.setState(JFrame.NORMAL);
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
    }

    private void initializeHiddenCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Point hotSpot = new Point(0, 0);
        BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
        invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "invisibleCursor");
    }

    private void initializeDevice() {
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        windowedDisplayMode = device.getDisplayMode();
        System.out.println("windowedMode : " + windowedDisplayMode.getWidth() + "X" + windowedDisplayMode.getHeight());
    }
}
