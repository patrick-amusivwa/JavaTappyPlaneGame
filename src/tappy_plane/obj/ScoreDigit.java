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
public class ScoreDigit extends TappyPlaneObj {

    private int digitWidth;
    private int digitPosition;
    private int scoreDigitsCount;
    
    public ScoreDigit(TappyPlaneGame scene, int digitPosition) {
        super(scene);
        this.digitPosition = digitPosition;
    }

    @Override
    public void init() {
        loadDigitImages();
        digitWidth = images[0].getWidth();
    }
    
    private void loadDigitImages() {
        createImages(10);
        for (int i=0; i<10; i++) {
            images[i] = Asset.loadImage("number" + i +".png");
        }
    }
    
    @Override
    public void updatePlaying() {
        String scoreString = game.getScore() + "";
        scoreDigitsCount = scoreString.length();
        visible = digitPosition < scoreDigitsCount;
        if (visible) {
            char digit = scoreString.charAt(digitPosition);
            image = images[digit - 48];
            y = 30;
            x = (Engine.screenSize.width - (scoreDigitsCount + 1) * digitWidth); 
            x += digitWidth * digitPosition;
        }
    }

    // broadcast messages
    
    public void stateChanged() {
        visible = game.getState() == State.PLAYING;
    }
    
}
