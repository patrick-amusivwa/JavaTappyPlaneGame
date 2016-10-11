package tappy_plane.obj;

import infra.Asset;
import infra.Engine;
import infra.Mouse;
import infra.Time;
import java.awt.Rectangle;
import tappy_plane.TappyPlaneGame;
import tappy_plane.TappyPlaneGame.State;
import tappy_plane.TappyPlaneObj;

/**
 *
 * @author leonardo
 */
public class Restart extends TappyPlaneObj {

    private boolean waitForFadeOut;
    private boolean fadeOutCompleted;
    
    public Restart(TappyPlaneGame scene) {
        super(scene);
    }

    @Override
    public void init() {
        loadImages();
        x = Engine.screenSize.width * 0.5;
        y = Engine.screenSize.height * 0.55;
        pivotX = 50;
        pivotY = 50;
        collider = new Rectangle(0, 0, 100, 100);
    }
    
    private void loadImages() {
        createImages(2);
        images[0] = Asset.loadImage("restart_gray.png");
        images[1] = Asset.loadImage("restart.png");
    }

    @Override
    public void updateGameOver() {
        alpha += Time.delta * 0.000000001;
        if (alpha > 1) {
            alpha = 1;
        }

        if (waitForFadeOut && fadeOutCompleted) {
            game.setState(State.GET_READY);
            game.broadcastMessage("restart");
            game.broadcastMessage("fadeIn");
            game.clearScore();
        }
        
        image = images[0];
        
        if (isPointInsideCollider(Mouse.x, Mouse.y) && alpha == 1) {
            image = images[1];
            if (!waitForFadeOut && Mouse.pressed && !Mouse.pressedConsumed) {
                Mouse.pressedConsumed = true;
                scaleX = 0.85;
                scaleY = 0.85;
                game.broadcastMessage("fadeOut");
                waitForFadeOut = true;
            }
        }
        
        if (!Mouse.pressed) {
            scaleX = 1;
            scaleY = 1;
        }
    }
    
    // broadcast messages

    public void stateChanged() {
        visible = false;
        if (game.getState() == State.GAME_OVER) {
            game.updateBestScore();
            visible = true;
            waitForFadeOut = false;
            fadeOutCompleted = false;
            alpha = 0;
        }
    }
    
    public void fadeOutCompleted() {
        fadeOutCompleted = true;
    }
    
}
