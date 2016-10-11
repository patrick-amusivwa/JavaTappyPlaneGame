package tappy_plane.obj;

import infra.Asset;
import infra.Engine;
import infra.Mouse;
import infra.Time;
import java.awt.Polygon;
import tappy_plane.TappyPlaneGame;
import tappy_plane.TappyPlaneGame.State;
import tappy_plane.TappyPlaneObj;

/**
 *
 * @author leonardo
 */
public class Plane extends TappyPlaneObj {

    private boolean fadeInCompleted;
    private double velocity;

    public Plane(TappyPlaneGame game) {
        super(game);
    }

    @Override
    public void init() {
        loadAnimationImages();
        setCollider();
        x = Engine.screenSize.getWidth() / 2;
        y = Engine.screenSize.getHeight() / 2;
        pivotX = images[0].getWidth() / 2;
        pivotY = images[0].getHeight() / 2;
    }
    
    private void setCollider() {
        Polygon polygonCollider = new Polygon();
        polygonCollider.addPoint(2, 15);
        polygonCollider.addPoint(9, 12);
        polygonCollider.addPoint(16, 13);
        polygonCollider.addPoint(22, 18);
        polygonCollider.addPoint(25, 11);
        polygonCollider.addPoint(22, 4);
        polygonCollider.addPoint(25, 0);
        polygonCollider.addPoint(69, -1);
        polygonCollider.addPoint(74, 7);
        polygonCollider.addPoint(66, 11);
        polygonCollider.addPoint(66, 16);
        polygonCollider.addPoint(75, 19);
        polygonCollider.addPoint(83, 18);
        polygonCollider.addPoint(86, 38);
        polygonCollider.addPoint(83, 65);
        polygonCollider.addPoint(72, 60);
        polygonCollider.addPoint(67, 63);
        polygonCollider.addPoint(64, 70);
        polygonCollider.addPoint(54, 71);
        polygonCollider.addPoint(45, 60);
        polygonCollider.addPoint(36, 60);
        polygonCollider.addPoint(25, 62);
        polygonCollider.addPoint(19, 55);
        polygonCollider.addPoint(23, 47);
        polygonCollider.addPoint(9, 30);
        polygonCollider.addPoint(2, 25);
        collider = polygonCollider;
        //collider = new Rectangle(0, 0, images[0].getWidth(), images[0].getHeight());
    }
    
    private void loadAnimationImages() {
        createImages(3);
        images[0] = Asset.loadImage("planeRed1.png");
        images[1] = Asset.loadImage("planeRed2.png");
        images[2] = Asset.loadImage("planeRed3.png");
    }

    @Override
    public void preUpdate() {
        // update animation frame
        image = images[(int) (Time.currentTime * 0.00000005) % 3];
        
        // update velocity and y position
        velocity = velocity + Time.delta * 0.0000001;
        velocity = velocity < -70 ? -70 : velocity;
        velocity = velocity >  70 ?  70 : velocity;
        y += velocity * Time.delta * 0.00000001;
        
        // spawn puff particle
        if ((int) (Time.currentTime * 0.0000001) % 10 == 0) {
            game.spawnFreePuffParticle();
        }
    }

    @Override
    public void updateGetReady() {
        // "jump" automatically when 'get ready' state
        if (y > 250) {
            y = 250;
            velocity = -30;
            game.broadcastMessage("tap");
        }
        
        // handle rotation
        double targetAngle = Math.PI * 0.25 * (velocity / 70) - Math.PI * 0.1;
        angle = angle + (targetAngle - angle) * 0.1;
        
        // if click mouse button, start playing
        if (Mouse.pressed && !Mouse.pressedConsumed && fadeInCompleted) {
            Mouse.pressedConsumed = true;
            game.setState(State.PLAYING);
            velocity = -30;
        }
    }

    @Override
    public void updatePlaying() {
        // "jump" when press mouse button
        if (Mouse.pressed && !Mouse.pressedConsumed) {
            Mouse.pressedConsumed = true;
            velocity -= 60;
        }
        
        // handle rotation
        double targetAngle = Math.PI * 0.25 * (velocity / 70) - Math.PI * 0.1;
        angle = angle + (targetAngle - angle) * 0.1;
        
        // if touch obstacle then game over
        if (game.checkCollision(this, Obstacle.class) || y < 0) {
            game.shakeScreen();
            game.setState(State.GAME_OVER);
            velocity = -60;
            fadeInCompleted = false;
        }
    }

    @Override
    public void updateGameOver() {
        angle += Time.delta * 0.00000002;
    }
    
    // --- broadcast messages ---
    
    public void restart() {
        x = Engine.screenSize.getWidth() / 2;
        y = Engine.screenSize.getHeight() / 2;
        angle = 0;
    }
    
    public void fadeInCompleted() {
        fadeInCompleted = true;
    }
    
}
