package GUI;

import GUIModel.*;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

/**
 * Auswahlleiste der Anwendung
 */
public class SelectorPanel extends JPanel implements ModelObserver {

    private ViewModel model;

    private JLabel opvSelectorTitleLabel;
    private JComboBox<String> opvSelector;
    private JLabel eReiheSelectorTitleLabel;
    private JComboBox<String> eReiheSelector;

    /**
     * Konstruktor des SelectorPanels
     * 
     * @param model ViewModel der Anwendung
     */
    public SelectorPanel(ViewModel model) {
        this.model = model;
        model.addOPVObserver(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create the label for the opv selector title
        opvSelectorTitleLabel = new JLabel("Operationsverstärker:");
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
                model.setAusgangsspannung(null);
                model.setVerstärkung(null);
                model.setWiderstände(null);
            }
        });

        add(opvSelector);

        // Create the label for the eReihe selector title
        eReiheSelectorTitleLabel = new JLabel("E-Reihe:");
        add(eReiheSelectorTitleLabel);

        // add E-Reihen selector
        eReiheSelector = new JComboBox<>();
        eReiheSelector.addItem("Keine");
        eReiheSelector.addItem("E12");
        eReiheSelector.addItem("E24");
        eReiheSelector.addItem("E48");
        eReiheSelector.addItem("E96");
        eReiheSelector.addItem("E192");
        eReiheSelector.setSelectedIndex(0);
        model.setSelectedEReihe((String) eReiheSelector.getSelectedItem());

        // Add ActionListener to handle E-Reihe selection changes
        eReiheSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEReihe = (String) eReiheSelector.getSelectedItem();
                model.setSelectedEReihe(selectedEReihe);
            }
        });

        add(eReiheSelector);

    }

    @Override
    public void update() {
    }
}
