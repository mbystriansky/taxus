package sk.tnet.display;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class MainFrame extends Frame {
    private static final long serialVersionUID = 1L;

    Logger LOG = LoggerFactory.getLogger(MainFrame.class);

    @Autowired
    ResourceLoader resourceLoader;

    private Graphics2D graphics2d;

    public MainFrame() {
        super(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());

        setUndecorated(true);
        setIgnoreRepaint(false);
        setResizable(false);
        setBackground(Color.BLACK);

        // Transparent 16 x 16 pixel cursor image
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        // Create a new blank cursor
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        // Set the blank cursor to the Frame
        setCursor(blankCursor);

        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        graphics2d = (Graphics2D) getGraphics();
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }

    @PostConstruct
    public void init() {
        try {
            drawURL(resourceLoader.getResource("boot_img.jpg").getURL());
        } catch (IOException e) {
            LOG.error("Chyba IO", e);
        }
    }

    public void drawImage(Image img) {
        graphics2d.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }

    public void drawURL(String url) {
        try {
            drawURL(new URL(url));
        } catch (MalformedURLException e) {
            LOG.error("Nespravna URL", e);
        }
    }
    
    public void drawURL(URL url) {
        try {
            Image img = ImageIO.read(url);
            drawImage(img);
        } catch (IOException e) {
            LOG.error("Chyba IO", e);
        }
    }

}
