package tappy_plane.obj;

import infra.Asset;
import infra.Engine;
import infra.Time;
import tappy_plane.TappyPlaneGame;
import tappy_plane.TappyPlaneObj;

/**
 *
 * @author leonardo
 */
public class FadeEffect extends TappyPlaneObj {
    
    private int sign;
    
    public FadeEffect(TappyPlaneGame scene) {
        super(scene);
    }

    @Override
    public void init() {
        image = Asset.loadImage("black.png");
        scaleX = Engine.screenSize.getWidth() / image.getWidth();
        scaleY = Engine.screenSize.getHeight() / image.getHeight();
    }

    @Override
    public void posUpdate() {
        alpha += Time.delta * 0.000000001 * sign;
        if (alpha < 0) {
            alpha = 0;
            sign = 0;
            visible = false;
            game.broadcastMessage("fadeInCompleted");
        }
        else if (alpha > 1) {
            alpha = 1;
            sign = 0;
            game.broadcastMessage("fadeOutCompleted");
        }
    }

    // broadcast messages
    
    public void fadeIn() {
        visible = true;
        alpha = 1;
        sign = -1;
    }

    public void fadeOut() {
        visible = true;
        alpha = 0;
        sign = 1;
    }
    
}
