package infra;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author leonardo
 */
public abstract class Obj<T extends Game> {

    public T game;
    public double x, y;
    public double scaleX = 1, scaleY = 1;
    public double pivotX, pivotY;
    public double angle;
    public double alpha = 1;
    
    private static Composite[] alphaComposite = new AlphaComposite[256];
    
    public AffineTransform transform = new AffineTransform();
    
    public boolean visible = true;
    
    public BufferedImage image;
    public BufferedImage[] images;
    
    public Color colliderDebugColor = new Color(0, 0, 255, 127);
    public Shape collider;
    
    private Path2D transformedCollider = new Path2D.Double();

    public Obj(T scene) {
        this.game = scene;
        for (int i=0; i<256; i++) {
            alphaComposite[i] = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (i / 255f));
        }
        alphaComposite[255] = null;
    }
    
    public void createImages(int size) {
        images = new BufferedImage[size];
    }
    
    public void init() {
        // override your code here
    }
    
    public void update() {
        // override your code here
    }
    
    public AffineTransform getTranform() {
        transform.setToIdentity();
        transform.translate(x, y);
        transform.rotate(angle);
        transform.scale(scaleX, scaleY);
        transform.translate(-pivotX, -pivotY);
        return transform;
    }

    private void transformCollider() {
        transformedCollider.reset();
        transformedCollider.append(collider, true);
        transformedCollider.transform(getTranform());
    }
    
    public void draw(Graphics2D g) {
        if (!visible) {
            return;
        }
        if (alphaComposite[255] == null) {
            alphaComposite[255] = g.getComposite();
        }
        g.setComposite(alphaComposite[(int) (255 * alpha)]);
        g.drawImage(image, getTranform(), null);
        if (collider != null && Engine.DEBUG_DRAW_COLLIDER) {
            transformCollider();
            g.setColor(colliderDebugColor);
            g.fill(transformedCollider);
            g.setColor(Color.YELLOW);
            g.draw(transformedCollider);
        }
    }
    
    public boolean isPointInsideCollider(double x, double y) {
        transformCollider();
        return transformedCollider.contains(x, y);
    }

}
