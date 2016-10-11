package infra;

/**
 *
 * @author leonardo
 */
public class Time {

    
    public static long delta;
    public static long currentTime;
    private static long lastUpdatedTime = System.nanoTime();
    
    public static void update() {
        currentTime = System.nanoTime();
        delta = currentTime - lastUpdatedTime;
        lastUpdatedTime = currentTime;
    }
    
}
