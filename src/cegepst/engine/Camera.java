package cegepst.engine;


import cegepst.testGame.Player;

public class Camera {

    private int x;
    private int y;


    private static Camera instance;

    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }
        return instance;
    }

    private Camera() {
        x = 0;
        y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void position(Player player) {
        //change le y de la camera
        /*if (player.getLastY() > player.getY()) {
            y += player.getMomentum();
        } else if (player.getLastY() < player.getY()) {
            y -= player.getMomentum();
        }
        if (player.getY() < 200) {
            y += player.getMomentum();
        } else {
            y = 0;
        }*/
        x -= player.getHorizontalSpeed();
    }


}
