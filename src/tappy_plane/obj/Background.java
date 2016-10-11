package tappy_plane.obj;

import infra.Asset;
import infra.Time;
import tappy_plane.TappyPlaneGame;
import tappy_plane.TappyPlaneObj;

/**
 *
 * @author leonardo
 */
public class Background extends TappyPlaneObj {
    
    private int startX;
    
    public Background(TappyPlaneGame scene, int startX) {
        super(scene);
        this.startX = startX;
        x = startX;
    }

    @Override
    public void init() {
        image = Asset.loadImage("background.png");
    }

    @Override
    public void preUpdate() {
        x -= Time.delta * 0.00000005;
        if (x < (-image.getWidth() + startX)) {
            x = startX;
        }
    }
    
}
