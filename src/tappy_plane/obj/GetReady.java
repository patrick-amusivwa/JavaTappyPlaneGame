package tappy_plane.obj;

import infra.Asset;
import infra.Engine;
import infra.Time;
import tappy_plane.TappyPlaneGame;
import tappy_plane.TappyPlaneGame.State;
import tappy_plane.TappyPlaneObj;

/**
 *
 * @author leonardo
 */
public class GetReady extends TappyPlaneObj {

    public GetReady(TappyPlaneGame scene) {
        super(scene);
    }

    @Override
    public void init() {
        image = Asset.loadImage("textGetReady.png");
        pivotX = 200;
        pivotY = 36;
        x = Engine.screenSize.width * 0.5;
    }

    @Override
    public void updateGetReady() {
        y = Engine.screenSize.height * 0.3;
        y += 10 * Math.sin(Time.currentTime * 0.000000005);
    }
    
    // broadcast messages

    public void stateChanged() {
        visible = game.getState() == State.GET_READY;
    }
    
}
