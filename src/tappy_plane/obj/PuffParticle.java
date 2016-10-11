package tappy_plane.obj;

import infra.Asset;
import infra.Time;
import tappy_plane.TappyPlaneGame;
import tappy_plane.TappyPlaneObj;

/**
 *
 * @author leonardo
 */
public class PuffParticle extends TappyPlaneObj {
    
    private boolean free = true;
    private Plane plane;
    private double dy;
    
    public PuffParticle(TappyPlaneGame scene, Plane plane) {
        super(scene);
        this.plane = plane;
        this.x = plane.x;
        this.y = plane.y;
        this.dy = Math.random() * 0.5 - 0.25;
        pivotX = 12;
        pivotY = 10;
        visible = false;
    }

    public boolean isFree() {
        return free;
    }

    @Override
    public void init() {
        image = Asset.loadImage("puffSmall.png");
    }

    @Override
    public void posUpdate() {
        x -= Time.delta * 0.0000002;
        y += dy * Time.delta * 0.00000025;
        angle += Time.delta * 0.000000005;
        scaleX += Time.delta * 0.000000004;
        scaleY += Time.delta * 0.000000004;
        alpha -= Time.delta * 0.000000002;
        if (alpha < 0) {
            alpha = 0;
            visible = false;
            free = true;
        }
    }

    public void spawn() {
        alpha = 1;
        x = plane.x - 50;
        y = plane.y;
        scaleX = 1;
        scaleY = 1;
        dy = Math.random() * 0.5 - 0.25;
        visible = true;
        free = false;
    }
    
}
