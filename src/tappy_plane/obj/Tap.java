package tappy_plane.obj;

import infra.Asset;
import infra.Engine;
import tappy_plane.TappyPlaneGame;
import tappy_plane.TappyPlaneGame.State;
import tappy_plane.TappyPlaneObj;

/**
 *
 * @author leonardo
 */
public class Tap extends TappyPlaneObj {
    
    private boolean tap;
    private long tapTime;
    
    public Tap(TappyPlaneGame game) {
        super(game);
    }

    @Override
    public void init() {
        loadAnimation();
        x = Engine.screenSize.width * 0.52;
        y = Engine.screenSize.height * 0.75;
        pivotX = 50;
        pivotY = 59;
    }

    private void loadAnimation() {
        createImages(2);
        images[0] = Asset.loadImage("tap.png");
        images[1] = Asset.loadImage("tapTick.png");
    }
    
    @Override
    public void updateGetReady() {
        if (tap && System.currentTimeMillis() - tapTime < 75) {
            image = images[1];
            angle = 0;
        }
        else {
            image = images[0];
            angle = 0.2;
        }
    }

    // broadcast messages

    public void stateChanged() {
        visible = game.getState() == State.GET_READY;
    }
    
    public void tap() {
        tap = true;
        tapTime = System.currentTimeMillis();
    }

}
