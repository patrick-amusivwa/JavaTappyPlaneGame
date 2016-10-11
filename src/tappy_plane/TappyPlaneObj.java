package tappy_plane;

import infra.Obj;
import static tappy_plane.TappyPlaneGame.State.*;

/**
 *
 * @author leonardo
 */
public abstract class TappyPlaneObj extends Obj<TappyPlaneGame> {
    
    public TappyPlaneObj(TappyPlaneGame scene) {
        super(scene);
    }

    @Override
    public final void update() {
        preUpdate();
        switch(game.getState()) {
            case INIT: updateInit(); break;
            case GET_READY: updateGetReady(); break;
            case PLAYING: updatePlaying(); break;
            case GAME_OVER: updateGameOver(); break;
        }
        posUpdate();
    }

    public void preUpdate() {
    }    
    
    public void updateInit() {
    }    
    
    public void updateGetReady() {
    }    
    
    public void updatePlaying() {
    }    
    
    public void updateGameOver() {
    }    
    
    public void posUpdate() {
    }    
    
}
