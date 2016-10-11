package tappy_plane.obj;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import tappy_plane.TappyPlaneGame;
import tappy_plane.TappyPlaneObj;

/**
 *
 * @author leonardo
 */
public class BestScore extends TappyPlaneObj {

    private Font font = new Font("Arial", Font.PLAIN, 24);
    
    public BestScore(TappyPlaneGame scene) {
        super(scene);
    }

    @Override
    public void draw(Graphics2D g) {
        if (game.getBestScore() > 0) {
            drawText(g, "Best score: " + game.getBestScore(), 10, 25);
        }
    }
    
    private void drawText(Graphics2D g, String text, int x, int y) {
        g.setFont(font);
        g.setColor(Color.ORANGE);
        for (int y2=y-2; y2<=y+2; y2++) {
            for (int x2=x-2; x2<=x+2; x2++) {
                g.drawString(text, x2, y2);
            }
        }
        g.setColor(Color.WHITE);
        g.drawString(text, x, y);
    }
    
}
