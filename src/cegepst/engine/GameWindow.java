package cegepst.engine;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private JPanel panel;

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
    }

}
