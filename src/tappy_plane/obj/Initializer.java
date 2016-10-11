package tappy_plane.obj;

import infra.Time;
import tappy_plane.TappyPlaneGame;
import tappy_plane.TappyPlaneObj;

/**
 *
 * @author leonardo
 */
public class Initializer extends TappyPlaneObj {
    
    private long startTime;
    
    public Initializer(TappyPlaneGame scene) {
        super(scene);
    }

    @Override
    public void updateInit() {
        if (startTime == 0) {
            startTime = Time.currentTime;
            return;
        }
        
        if (Time.currentTime - startTime > 1000000000) {
            game.broadcastMessage("restart");
            game.broadcastMessage("fadeIn");
            game.setState(TappyPlaneGame.State.GET_READY);
        }
    }
    
}
