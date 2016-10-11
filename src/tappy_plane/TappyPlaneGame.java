package tappy_plane;

import infra.Game;
import infra.Time;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import tappy_plane.obj.Background;
import tappy_plane.obj.BestScore;
import tappy_plane.obj.FadeEffect;
import tappy_plane.obj.GameOver;
import tappy_plane.obj.GetReady;
import tappy_plane.obj.Ground;
import tappy_plane.obj.Initializer;
import tappy_plane.obj.Plane;
import tappy_plane.obj.PuffParticle;
import tappy_plane.obj.Restart;
import tappy_plane.obj.RockDown;
import tappy_plane.obj.RockUp;
import tappy_plane.obj.ScoreDigit;
import tappy_plane.obj.Tap;

/**
 *
 * @author leonardo
 */
public class TappyPlaneGame extends Game {

    public static enum State { INIT, GET_READY, PLAYING, GAME_OVER };
    private State state = State.INIT;
    private int rockHeight;
    private int score;
    private int bestScore;
    private List<PuffParticle> cachedPuffParticles = new ArrayList<PuffParticle>();

    private boolean shakeScreen = false;
    private long shakeScreenStartTime;
    
    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (this.state != state) {
            this.state = state;
            broadcastMessage("stateChanged");
        }
    }

    public int getNextRandomRockHeight() {
        rockHeight = (int) (145 * Math.random()) + 170;
        return rockHeight;
    }

    public int getRockHeight() {
        return rockHeight;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void clearScore() {
        score = 0;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void updateBestScore() {
        if (score > bestScore) {
            bestScore = score;
        }
    }

    public void spawnFreePuffParticle() {
        for (PuffParticle puff : cachedPuffParticles) {
            if (puff.isFree()) {
                puff.spawn();
                return;
            }
        }
    }
    
    @Override
    public void init() {
        addObj(new Initializer(this));
        addObj(new Background(this, 0));
        addObj(new Background(this, 800));
        addObj(new Ground(this, 0));
        addObj(new Ground(this, 808));

        // rock up and down (obstacles)
        for (int x=0; x<800; x+=230) {
            addObj(new RockUp(this, x + 800, getNextRandomRockHeight()));
            addObj(new RockDown(this, x + 800, getRockHeight()));
        }
        
        Plane plane = new Plane(this);
        
        // create cached puff particles
        for (int i=0; i<10; i++) {
            PuffParticle puffParticle = new PuffParticle(this, plane);
            addObj(puffParticle);
            cachedPuffParticles.add(puffParticle);
        }
        
        addObj(plane);
        
        // score digits
        for (int i=0; i<10; i++) {
            addObj(new ScoreDigit(this, i));
        }
        
        addObj(new BestScore(this));
        addObj(new GetReady(this));
        addObj(new Tap(this));
        addObj(new GameOver(this));
        addObj(new Restart(this));
        addObj(new FadeEffect(this));
    }
    
    public void shakeScreen() {
        shakeScreen = true;
        shakeScreenStartTime = Time.currentTime;
    }

    @Override
    public void draw(Graphics2D g) {
        if (shakeScreen) {
            AffineTransform at = g.getTransform();
            int sx = (int) (40 * Math.sin(Time.currentTime * 0.0000001));
            g.translate(sx, 0);
            super.draw(g);
            g.setTransform(at);
            if (Time.currentTime - shakeScreenStartTime > 500000000) {
                shakeScreen = false;
            }
        }
        else {
            super.draw(g);
        }
    }
    
}
