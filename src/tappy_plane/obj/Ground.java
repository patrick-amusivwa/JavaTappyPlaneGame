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
public class Ground extends Obstacle {
    
    private int startX;
    
    public Ground(TappyPlaneGame scene, int startX) {
        super(scene);
        this.startX = startX;
        this.x = startX;
    }

    @Override
    public void init() {
        image = Asset.loadImage("groundGrass.png");
        setCollider();
        y = Engine.screenSize.height - image.getHeight();
    }

    private void setCollider() {
        Polygon polygonCollider = new Polygon();
        polygonCollider.addPoint(0, 36);
        polygonCollider.addPoint(32, 33);
        polygonCollider.addPoint(40, 42);
        polygonCollider.addPoint(90, 47);
        polygonCollider.addPoint(135, 33);
        polygonCollider.addPoint(158, 9);
        polygonCollider.addPoint(246, 4);
        polygonCollider.addPoint(308, 29);
        polygonCollider.addPoint(351, 27);
        polygonCollider.addPoint(375, 12);
        polygonCollider.addPoint(434, 14);
        polygonCollider.addPoint(471, 46);
        polygonCollider.addPoint(508, 44);
        polygonCollider.addPoint(534, 55);
        polygonCollider.addPoint(571, 55);
        polygonCollider.addPoint(599, 29);
        polygonCollider.addPoint(636, 25);
        polygonCollider.addPoint(659, 3);
        polygonCollider.addPoint(743, -1);
        polygonCollider.addPoint(764, 27);
        polygonCollider.addPoint(807, 36);
        polygonCollider.addPoint(807, 70);
        polygonCollider.addPoint(1, 72);
        collider = polygonCollider;
    }

    @Override
    public void preUpdate() {
        x -= Time.delta * 0.0000001;
        if (x < (-image.getWidth() + startX)) {
            x = startX;
        }
    }
    
}
