package GUI;

import java.awt.*;

import javax.swing.*;

import GUIModel.ViewModel;

public class MainFrame extends JFrame {

    private ViewModel model;

    private ImagePanel imagePanel;
    private DataEntryPanel dataEntryPanel;
    private DataDisplayPanel dataDisplayPanel;
    private SelectorPanel selectorPanel;


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