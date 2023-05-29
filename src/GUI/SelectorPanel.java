package GUI;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

import GUIModel.*;


public class SelectorPanel extends JPanel implements ModelObserver {
    private Model model;

    private JLabel opvSelectorTitleLabel;
    private JComboBox<String> opvSelector;
    private JLabel eReiheSelectorTitleLabel;
    private JComboBox<String> eReiheSelector;
    private JButton calculateButton;
    
    public SelectorPanel(Model model) {
        this.model = model;
        model.addObserver(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create the label for the opv selector title
        opvSelectorTitleLabel = new JLabel("Wähle den Operationsverstärker:");
        add(opvSelectorTitleLabel);

        opvSelector = new JComboBox<>();
        opvSelector.addItem("Nichtinvertierer");
        opvSelector.addItem("Invertierer");
        opvSelector.addItem("Subtrahierer");
        opvSelector.addItem("Summierer");
        opvSelector.setSelectedIndex(0);

        // Add ActionListener to handle image selection changes
        opvSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedImage = (String) opvSelector.getSelectedItem();
                model.setSelectedOPV(selectedImage);
            }
        });

        add(opvSelector);

        // Create the label for the eReihe selector title
        eReiheSelectorTitleLabel = new JLabel("Wähle die E-Reihe:");
        add(eReiheSelectorTitleLabel);

        eReiheSelector = new JComboBox<>();
        eReiheSelector.addItem("E12");
        eReiheSelector.addItem("E24");
        eReiheSelector.setSelectedIndex(0);

        // Add ActionListener to handle E-Reihe selection changes
        eReiheSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedE12 = (String) eReiheSelector.getSelectedItem();
                model.setSelectedEReihe(selectedE12);
            }
        });

        add(eReiheSelector);

        // Create the calculate button
        calculateButton = new JButton("Berechnen");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.calculate();
            }
        });

    }

    @Override
    public void update() {
    }
}
