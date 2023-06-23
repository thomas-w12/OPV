package GUI;

import javax.imageio.ImageIO;

import exceptions.FalseInputException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {
    public static BufferedImage loadImage(String imagePath) throws Exception {

        // Get the URL of the resource from the classpath
        URL resourceUrl = ImageLoader.class.getResource(imagePath);
        if (resourceUrl == null) {
            throw new FalseInputException("Image not found: " + imagePath);
        }

        // Read the image using ImageIO
        return ImageIO.read(resourceUrl);

    }

}