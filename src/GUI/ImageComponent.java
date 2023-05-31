package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Klasse eines Bildes, welches in der Größe verändert werden kann und das
 * Seitenverhältnis beibehält
 * 
 * @author Thomas Wegele, Simon Prießnitz
 */
public class ImageComponent extends JComponent {

    private BufferedImage image;

    public ImageComponent() {
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Calculate the scaling factors for width and height
            double scaleX = (double) panelWidth / imageWidth;
            double scaleY = (double) panelHeight / imageHeight;

            // Use the smaller scaling factor to maintain aspect ratio
            double scale = Math.min(scaleX, scaleY);

            // Calculate the scaled size of the image
            int scaledWidth = (int) (scale * imageWidth);
            int scaledHeight = (int) (scale * imageHeight);

            // Calculate the x and y offset to center the image
            int xOffset = (panelWidth - scaledWidth) / 2;
            int yOffset = (panelHeight - scaledHeight) / 2;

            // Draw the scaled image
            g.drawImage(image, xOffset, yOffset, scaledWidth, scaledHeight, null);
        }
    }

}