package GUI;

import javax.swing.*;

import GUIModel.*;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel implements ModelObserver {
    private Model model;

    private ImageComponent imageComponent;

    public ImagePanel(Model model) {
        this.model = model;
        model.addOPVObserver(this);

        setLayout(new BorderLayout());

        // Create the ImageComponent
        imageComponent = new ImageComponent();
        add(imageComponent, BorderLayout.CENTER);
        displayImage(model.getSelectedOPV());

    }

    private void displayImage(String selectedImage) {
        BufferedImage image = null;
        try {
            switch (selectedImage) {
                case "Nichtinvertierer":
                    image = ImageIO.read(new File("src/resources/Nichtinvertierer.png"));
                    break;
                case "Invertierer":
                    image = ImageIO.read(new File("src/resources/Invertierer.png"));
                    break;
                case "Subtrahierer":
                    image = ImageIO.read(new File("src/resources/Subtrahierer.png"));
                    break;
                case "Summierer":
                    image = ImageIO.read(new File("src/resources/Summierer.png"));
                    break;
                default:
                    // Handle default case or display an error message
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageComponent.setImage(image);
    }

    @Override
    public void update() {
        displayImage(model.getSelectedOPV());
    }
}