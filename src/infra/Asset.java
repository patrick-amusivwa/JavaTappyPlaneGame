package infra;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author leo
 */
public class Asset {

    public static BufferedImage loadImage(String imageResource) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Asset.class.getResource("/res/" + imageResource));
        } catch (IOException ex) {
            Logger.getLogger(Obj.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        return image;
    }
    
}
