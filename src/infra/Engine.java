package infra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author leonardo
 */
public class Engine {

    public static final boolean DEBUG_DRAW_COLLIDER = false;
    
    public static final Dimension screenSize = new Dimension(800, 480);
    
    private static JFrame windows;
    private static Canvas canvas;
    private static BufferStrategy canvasBufferStrategy;
    private static Game scene;
    private static boolean running;
    
    public static void start(String title, Game startScene) {
        scene = startScene;
        startScene.init();
        
        MouseHandler mouseHandler = new MouseHandler();
        canvas = new Canvas();
        canvas.setBackground(Color.BLACK);
        canvas.addMouseListener(mouseHandler);
        canvas.addMouseMotionListener(mouseHandler);
        canvas.setSize(screenSize);

        windows = new JFrame();
        windows.setTitle(title);
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.add(canvas);
        windows.setResizable(false);
        windows.pack();
        windows.setLocationRelativeTo(null);
        windows.setVisible(true);
        
        canvas.createBufferStrategy(2);
        canvasBufferStrategy = canvas.getBufferStrategy();
        new Thread(new MainLoop()).start();
    }
    
    private static class MainLoop implements Runnable {
        @Override
        public void run() {
            running = true;
            while (running) {
                Time.update();
                scene.update();
                Graphics2D g = (Graphics2D) canvasBufferStrategy.getDrawGraphics();
                g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                scene.draw(g);
                g.dispose();
                canvasBufferStrategy.show();

                try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
    
    private static class MouseHandler extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent me) {
            Mouse.x = me.getX();
            Mouse.y = me.getY();
        }

        @Override
        public void mousePressed(MouseEvent me) {
            Mouse.pressed = true;
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            Mouse.pressed = false;
            Mouse.pressedConsumed = false;
        }
        
    }
    
}
