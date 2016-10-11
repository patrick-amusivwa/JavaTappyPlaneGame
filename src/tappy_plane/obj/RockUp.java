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
public class RockUp extends Obstacle {
    
    private boolean scored;
    private int startX;
    
    public RockUp(TappyPlaneGame scene, int startX, int startY) {
        super(scene);
        this.startX = startX;
        x = startX;
        y = startY;
        pivotY = -80;
    }

    @Override
    public void init() {
        image = Asset.loadImage("rockGrass.png");
        setCollider();
    }

    private void setCollider() {
        Polygon polygonCollider = new Polygon();
        polygonCollider.addPoint(0, 238);
        polygonCollider.addPoint(29, 131);
        polygonCollider.addPoint(38, 122);
        polygonCollider.addPoint(61, 0);
        polygonCollider.addPoint(69, 0);
        polygonCollider.addPoint(80, 103);
        polygonCollider.addPoint(85, 104);
        polygonCollider.addPoint(95, 173);
        polygonCollider.addPoint(100, 178);
        polygonCollider.addPoint(108, 238);
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
            scored = false;
        }
        
        // update score
        if (x < 300 && !scored) {
            game.incrementScore();
            scored = true;
        }
    }
    
    // --- broadcast messages ---

    public void restart() {
        scored = false;
        x = startX;
        y = game.getNextRandomRockHeight();
    }
    
}
