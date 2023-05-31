package GUI;

import GUIModel.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Panel, welches das Bild des Operationsverstärkers darstellt
 * 
 * @author Thomas Wegele, Simon Prießnitz
 */
public class ImagePanel extends JPanel implements ModelObserver {
    private ViewModel model;

    private ImageComponent imageComponent;

    /**
     * Konstruktor des Image Panels
     * 
     * @param model ViewModel der Anwendung
     */
    public ImagePanel(ViewModel model) {
        this.model = model;
        model.addOPVObserver(this);

        setLayout(new BorderLayout());

        // Create the ImageComponent
        imageComponent = new ImageComponent();
        EmptyBorder border = new EmptyBorder(30, 30, 30, 30);
        imageComponent.setBorder(border);
        add(imageComponent, BorderLayout.CENTER);

        // Initial update of content
        update();
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
                    JOptionPane.showMessageDialog(this, "Fehler, Bild nicht verfügbar", "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fehler, Bild nicht verfügbar", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        imageComponent.setImage(image);
    }

    /**
     * Implementation der der update-Methode zur Aktualisieren der GUI nach
     * Änderungen des ViewModels
     */
    @Override
    public void update() {
        displayImage(model.getSelectedOPV());
    }
}