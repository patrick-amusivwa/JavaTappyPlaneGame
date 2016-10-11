package main;

import infra.Engine;
import infra.Game;
import javax.swing.SwingUtilities;
import tappy_plane.TappyPlaneGame;

/**
 *
 * @author leonardo
 */
public class Main {
    
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game tappyPlaneGame = new TappyPlaneGame();
                Engine.start("Tappy Plane - by O.L.", tappyPlaneGame);
            }
        });
    }
    
}
