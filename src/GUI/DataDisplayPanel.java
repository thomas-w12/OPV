package GUI;

import javax.swing.*;
import GUIModel.*;
import java.awt.*;

public class DataDisplayPanel extends JPanel implements ModelObserver {

    private Model model;

    private JLabel outputVoltageTitleLabel;
    private JLabel amplificationTitleLabel;
    private JLabel outputVoltageLabel;
    private JLabel amplificationLabel;

    private String outputVoltageString;
    private String amplificationString;

    public DataDisplayPanel(Model model) {
        this.model = model;
        model.addObserver(this);

        setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 20, 5, 20); // Add padding between components

        // Create the label for the output voltage title
        outputVoltageTitleLabel = new JLabel("Ausgangsspannung:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(outputVoltageTitleLabel, constraints);

        // Create the label for the output voltage
        outputVoltageLabel = new JLabel("U_a = ?");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(outputVoltageLabel, constraints);

        // Create the label for the amplification title
        amplificationTitleLabel = new JLabel("Verstärkung:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(amplificationTitleLabel, constraints);

        // Create the label for the amplification
        amplificationLabel = new JLabel("V = ?");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(amplificationLabel, constraints);
    }

    @Override
    public void update() {

        if (model.getAusgangsspannung() == null) {
            outputVoltageString = "U_a = ?";
        } else {
            outputVoltageString = "U_a = " + model.getAusgangsspannung().toString() + " V";
        }

        if (model.getVerstärkung() == null) {
            amplificationString = "V = ?";
        } else {
            amplificationString = "V = " + model.getVerstärkung().toString();
        }

        outputVoltageLabel.setText(outputVoltageString);
        amplificationLabel.setText(amplificationString);

    }

}
