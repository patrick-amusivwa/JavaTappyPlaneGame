package tappy_plane.obj;

import infra.Asset;
import infra.Engine;
import infra.Time;
import java.awt.Polygon;
import tappy_plane.TappyPlaneGame;

/**
 *
 * @author leonardo
 */
public class RockDown extends Obstacle {
    
    private int startX;
    
    public RockDown(TappyPlaneGame scene, int startX, int startY) {
        super(scene);
        this.startX = startX;
        x = startX;
        y = startY;
        pivotY = 239 + 80;
    }

    @Override
    public void init() {
        image = Asset.loadImage("rockGrassDown.png");
        setCollider();
    }

    private void setCollider() {
        Polygon polygonCollider = new Polygon();
        polygonCollider.addPoint(1, 1);
        polygonCollider.addPoint(28, 107);
        polygonCollider.addPoint(37, 114);
        polygonCollider.addPoint(62, 236);
        polygonCollider.addPoint(69, 237);
        polygonCollider.addPoint(81, 135);
        polygonCollider.addPoint(85, 135);
        polygonCollider.addPoint(93, 65);
        polygonCollider.addPoint(99, 60);
        polygonCollider.addPoint(106, -1);
        collider = polygonCollider;
    }

    @Override
    public void updatePlaying() {
        internalUpdate();
    }

    @Override
    public void updateGameOver() {
        internalUpdate();
    }
    
    private void internalUpdate() {
        x -= Time.delta * 0.0000001;
        if (x <= -image.getWidth()) {
            x = Engine.screenSize.width;
        }
    }

    // --- broadcast messages ---
    
    public void restart() {
        x = startX;
        y = game.getRockHeight();
    }
    
}
