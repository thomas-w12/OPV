package GUI;

import GUIModel.ViewModel;

import java.awt.*;
import javax.swing.*;

/**
 * Hauptfenster der Anwendung
 * 
 * @author Thomas Wegele, Simon Prießnitz
 */
public class MainFrame extends JFrame {

    private ViewModel model;

    private ImagePanel imagePanel;
    private DataEntryPanel dataEntryPanel;
    private DataDisplayPanel dataDisplayPanel;
    private SelectorPanel selectorPanel;

    /**
     * Konstruktor des MainFrames, dieser erzeugt das ViewModel, welches während der
     * Lebensdauer dieses Fensters aktiv ist
     */
    public MainFrame() {
        // create main Application model
        model = new ViewModel();

        // Set main frame properties
        setTitle("Operationsverstärker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());

        /**
         * Create and configure UI components
         **/

        // Create the DataEntryPanel
        dataEntryPanel = new DataEntryPanel(model);

        // Create the DataDisplayPanel
        dataDisplayPanel = new DataDisplayPanel(model);

        // create selectorPanel
        selectorPanel = new SelectorPanel(model);

        // create ImagePanel2
        imagePanel = new ImagePanel(model);

        // Add components to the frame
        getContentPane().add(dataEntryPanel, BorderLayout.WEST);
        getContentPane().add(dataDisplayPanel, BorderLayout.EAST);
        getContentPane().add(selectorPanel, BorderLayout.NORTH);
        getContentPane().add(imagePanel, BorderLayout.CENTER);
    }
}