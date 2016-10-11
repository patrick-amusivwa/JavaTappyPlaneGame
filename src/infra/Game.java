package infra;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leonardo
 */
public abstract class Game {
    
    private List<Obj> objs = new ArrayList<Obj>();

    public Game() {
    }

    public void init() {
        // override your code here
    }
    
    public void addObj(Obj obj) {
        objs.add(obj);
        obj.init();
    }
    
    public void update() {
        for (Obj obj : objs) {
            obj.update();
        }
    }
    
    public void draw(Graphics2D g) {
        for (Obj obj : objs) {
            obj.draw(g);
        }
    }
    
    public boolean checkCollision(Obj o1, Class collidedObjType) {
        for (Obj o2 : objs) {
            if (o1 == o2 || !collidedObjType.isInstance(o2)
                || o1.collider == null || o2.collider == null 
                || !o1.visible || !o2.visible) {
                    continue;
            }
            Area a1 = new Area(o1.collider);
            Area a2 = new Area(o2.collider);
            a1.transform(o1.getTranform());
            a2.transform(o2.getTranform());
            a1.intersect(a2);
            if (!a1.isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public void broadcastMessage(String message) {
        for (Obj obj : objs) {
            try {
                Method method = obj.getClass().getMethod(message);
                if (method != null) {
                    method.invoke(obj);
                }
            } catch (Exception ex) {
            }
        }
    }
    
}
