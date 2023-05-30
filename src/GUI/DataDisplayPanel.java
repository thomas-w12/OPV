package GUI;

import javax.swing.*;
import GUIModel.*;
import widerstand.Widerstand;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;

public class DataDisplayPanel extends JPanel implements ModelObserver {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private ViewModel model;

    private JPanel outputDisplayPanel;

    private JLabel outputVoltageTitleLabel;
    private JLabel amplificationTitleLabel;
    private JLabel outputVoltageLabel;
    private JLabel amplificationLabel;

    private String outputVoltageString;
    private String amplificationString;

    private JPanel resistorDisplayPanel;
    private JScrollPane resistorsDisplayScrollPane;

    public DataDisplayPanel(ViewModel model) {
        this.model = model;
        model.addOutputObserver(this);

        // new BoxLayout(this, BoxLayout.Y_AXIS)
        setLayout(new BorderLayout());

        /**
         * Create the "outputDisplayPanel" which holds the output voltage and amplification in a GridBagLayout JPanel
         */

        outputDisplayPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 0, 5, 20); // Add padding between components
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        // Create the label for the output voltage title
        outputVoltageTitleLabel = new JLabel("Ausgangsspannung:");
        constraints.gridy = 1;
        outputDisplayPanel.add(outputVoltageTitleLabel, constraints);

        // Create the label for the output voltage
        outputVoltageLabel = new JLabel("U_a = ?");
        constraints.gridy = 2;
        outputDisplayPanel.add(outputVoltageLabel, constraints);

        // Create the label for the amplification title
        amplificationTitleLabel = new JLabel("Verstärkung:");
        constraints.gridy = 3;
        outputDisplayPanel.add(amplificationTitleLabel, constraints);

        // Create the label for the amplification
        amplificationLabel = new JLabel("V = ?");
        constraints.gridy = 4;
        outputDisplayPanel.add(amplificationLabel, constraints);

        // Add extra spacing
        constraints.gridy = 5;
        outputDisplayPanel.add(Box.createVerticalStrut(10), constraints);
        /*
         * Create the resistor Display Panel
         */

        resistorDisplayPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(resistorDisplayPanel, BoxLayout.Y_AXIS); // Vertical layout
        resistorDisplayPanel.setLayout(boxLayout);

        resistorsDisplayScrollPane = new JScrollPane(resistorDisplayPanel);
        resistorsDisplayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel resistorView = new JPanel(new BorderLayout());
        JLabel resistorLabel = new JLabel("Widerstände:");
        resistorView.add(resistorLabel, BorderLayout.NORTH);
        resistorView.add(resistorsDisplayScrollPane, BorderLayout.CENTER);

        
        /**
         * add the components to the main DataDisplayPanel
         */
        add(outputDisplayPanel, BorderLayout.NORTH);
        add(resistorView, BorderLayout.CENTER);
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
            String keys[] = model.getWiderstände().keySet().toArray(new String[0]);
            Arrays.sort(keys);
            
            for (String key: keys) {

                String name = key;
                Widerstand widerstand = model.getWiderstände().get(name);
                //resistorDisplayPanel.add(new ResistorColorsDisplayPanel(name, widerstand));

                // Create a panel for each resistor entry
                ResistorColorsDisplayPanel resistorPanel = new ResistorColorsDisplayPanel(name, widerstand);

                // Set maximum size to preferred size to avoid vertical expansion
                resistorPanel.setMaximumSize(resistorPanel.getPreferredSize());

                resistorDisplayPanel.add(resistorPanel);
                
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
