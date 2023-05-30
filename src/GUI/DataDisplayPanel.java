package GUI;

import javax.swing.*;
import GUIModel.*;
import widerstand.EReihe;
import widerstand.Widerstand;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;

public class DataDisplayPanel extends JPanel implements ModelObserver {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private Model model;

    private JLabel outputVoltageTitleLabel;
    private JLabel amplificationTitleLabel;
    private JLabel outputVoltageLabel;
    private JLabel amplificationLabel;

    private String outputVoltageString;
    private String amplificationString;

    private JPanel resistorDisplayPanel;
    private JScrollPane resistorsDisplayScrollPane;

    public DataDisplayPanel(Model model) {
        this.model = model;
        model.addOutputObserver(this);

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

        // Create the resistor Display Panel

        resistorDisplayPanel = new JPanel();
        //BoxLayout boxLayout = new BoxLayout(resistorDisplayPanel, BoxLayout.Y_AXIS); // Vertical layout
        resistorDisplayPanel.setLayout(new GridBagLayout());

        resistorsDisplayScrollPane = new JScrollPane(resistorDisplayPanel);
        resistorsDisplayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel resistorView = new JPanel(new BorderLayout());
        JLabel resistorLabel = new JLabel("Widerstände:");
        resistorView.add(resistorLabel, BorderLayout.NORTH);
        resistorView.add(resistorsDisplayScrollPane, BorderLayout.CENTER);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(resistorView, constraints);

        // Push to Top
        constraints.gridy = 5;
        constraints.weighty = 1;
        add(Box.createGlue(), constraints);

        update();
    }

    @Override
    public void update() {

        if (model.getAusgangsspannung() == null) {
            outputVoltageString = "U_a = ?";
        } else {
            outputVoltageString = "U_a = " + df.format(model.getAusgangsspannung()).toString() + " V";
        }

        if (model.getVerstärkung() == null) {
            amplificationString = "V = ?";
        } else {
            amplificationString = "V = " + df.format(model.getVerstärkung()).toString();
        }

        outputVoltageLabel.setText(outputVoltageString);
        amplificationLabel.setText(amplificationString);

        resistorDisplayPanel.removeAll();

        try {
            HashMap<String, Widerstand> widerstände = model.getWiderstände();

            // Set the constraints for vertical alignment
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0; // X-coordinate in the grid
            gbc.gridy = 0; // Y-coordinate in the grid
            gbc.weightx = 1.0; // Horizontal weight for resizing
            gbc.weighty = 1; // Vertical weight for resizing
            gbc.fill = GridBagConstraints.BOTH; // Fill the cell both horizontally and vertically
            gbc.anchor = GridBagConstraints.CENTER; // Center the component within the cell

            for (HashMap.Entry<String, Widerstand> entry : widerstände.entrySet()) {
                String name = entry.getKey();
                Widerstand widerstand = entry.getValue();

                resistorDisplayPanel.add(new ResistorColorsDisplayPanel(name, widerstand), gbc);
                gbc.gridy++;
            }
        } catch (ResistorColorsNotAvailableException e) {
            String text = "<html><p style=\"width:100px\">" + e.getMessage() + "</p></html>";
            resistorDisplayPanel.add(new JLabel(text));
        } 
        catch (Exception e) {
            String text = "<html><p style=\"width:100px\">" + "Es sind noch keine Widerstände zum Anzeigen vorhanden."
                    + "</p></html>";
            resistorDisplayPanel.add(new JLabel(text));
        }

        revalidate();
        repaint();

    }

}
