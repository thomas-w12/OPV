package GUI;

import javax.swing.*;

import GUIModel.ViewModel;
import GUIModel.ModelObserver;
import operationsverstaerker.Invertierer;
import operationsverstaerker.NichtInvertierer;
import operationsverstaerker.Subtrahierverstärker;
import operationsverstaerker.Summierverstärker;
import widerstand.Widerstand;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataEntryPanel extends JPanel implements ModelObserver {

    private ViewModel model;

    private JPanel entriesPanel;
    private JPanel resistorBorderLayoutPanel;
    private JPanel voltageBorderLayoutPanel;

    private JPanel resistorPanel;
    private JPanel voltagePanel;
    private JScrollPane resistorScrollPane;
    private JScrollPane voltageScrollPane;

    private JLabel resistorLabel;
    private JLabel voltageLabel;
    private List<JLabel> resistorLabels;
    private List<JTextField> resistorTextFields;
    private List<JLabel> voltageLabels;
    private List<JTextField> voltageTextFields;

    private JPanel buttonPanel;
    private JPanel addButtonPanel;
    private JPanel calculateButtonPanel;

    private JButton addButton;
    private JButton removeButton;

    private JButton calculateButton;

    public DataEntryPanel(ViewModel model) {



        this.model = model;
        model.addOPVObserver(this);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding of 10 pixels
        resistorLabels = new ArrayList<>();
        resistorTextFields = new ArrayList<>();

        voltageLabels = new ArrayList<>();
        voltageTextFields = new ArrayList<>();

        // Create the panel to hold the entries
        entriesPanel = new JPanel();
        entriesPanel.setLayout(new GridBagLayout());

        // Create the resistor panel
        resistorPanel = new JPanel();
        BoxLayout resistorPanelLayout = new BoxLayout(resistorPanel, BoxLayout.Y_AXIS); // Vertical layout
        resistorPanel.setLayout(resistorPanelLayout);

        // Create the resistor scroll pane
        resistorScrollPane = new JScrollPane(resistorPanel);
        resistorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create the voltage panel
        voltagePanel = new JPanel();
        BoxLayout voltagePanelLayout = new BoxLayout(voltagePanel, BoxLayout.Y_AXIS); // Vertical layout
        voltagePanel.setLayout(voltagePanelLayout);

        // Create the voltage scroll pane
        voltageScrollPane = new JScrollPane(voltagePanel);
        voltageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create the resistor label
        resistorLabel = new JLabel("Widerstände:");

        // Create the voltage label
        voltageLabel = new JLabel("Spannungen:");

        // Set the constraints for vertical alignment
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // X-coordinate in the grid
        gbc.gridy = 0; // Y-coordinate in the grid
        gbc.weightx = 1.0; // Horizontal weight for resizing
        gbc.weighty = 0.5; // Vertical weight for resizing
        gbc.fill = GridBagConstraints.BOTH; // Fill the cell both horizontally and vertically
        gbc.anchor = GridBagConstraints.CENTER; // Center the component within the cell




        // entriesPanel.add(resistorLabel, gbc);
        // gbc.gridy = 1;
        // entriesPanel.add(resistorScrollPane, gbc);
        // gbc.gridy = 2;
        // entriesPanel.add(voltageLabel, gbc);
        // gbc.gridy = 3;
        // entriesPanel.add(voltageScrollPane, gbc);

        resistorBorderLayoutPanel = new JPanel(new BorderLayout());
        resistorBorderLayoutPanel.add(resistorLabel, BorderLayout.NORTH);
        resistorBorderLayoutPanel.add(resistorScrollPane, BorderLayout.CENTER);

        voltageBorderLayoutPanel = new JPanel(new BorderLayout());
        voltageBorderLayoutPanel.add(voltageLabel, BorderLayout.NORTH);
        voltageBorderLayoutPanel.add(voltageScrollPane, BorderLayout.CENTER);

        entriesPanel.add(resistorBorderLayoutPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0; // No vertical weight
        entriesPanel.add(new JPanel(), gbc);

        // Add the second BorderLayout to the GridBagLayout
        gbc.gridy = 2;
        gbc.weighty = 0.5; // Equal weight for the two Borderlayouts
        entriesPanel.add(voltageBorderLayoutPanel, gbc);



        // Wrap the entries panel in a scroll pane
        // JScrollPane scrollPane = new JScrollPane(entriesPanel);
        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the panel
        add(entriesPanel, BorderLayout.CENTER);


        // Create the "Add" button
        addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = resistorLabels.size();
                addDataEntryField("R_1" + index, resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("U_e" + index, voltagePanel, voltageLabels, voltageTextFields);
                revalidate();
                repaint();
            }
        });

        // Create the "Remove" button
        removeButton = new JButton("-");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int labelSize = resistorLabels.size();
                if (labelSize >= 4) {
                    removeDataEntryField(resistorPanel, resistorLabels, resistorTextFields);
                    removeDataEntryField(voltagePanel, voltageLabels, voltageTextFields);
                }
                revalidate();
                repaint();
            }
        });

        // Create the calculate Button
        calculateButton = new JButton("Berechnen");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
                revalidate();
                repaint();
            }
        });

        // Create a panel to hold the buttons
        buttonPanel = new JPanel();
        BoxLayout buttonPanelLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(buttonPanelLayout);

        addButtonPanel = new JPanel(new FlowLayout());

        // Add the "Add" button to the panel
        addButtonPanel.add(addButton);
        addButtonPanel.add(removeButton);

        // Calculate Button
        calculateButtonPanel = new JPanel(new FlowLayout());
        calculateButtonPanel.add(calculateButton);

        // Add the Panels to the Button Panel
        buttonPanel.add(addButtonPanel);
        buttonPanel.add(calculateButtonPanel);


        // Add the button panel to the main panel
        add(buttonPanel, BorderLayout.SOUTH);


        // Create the initial label and text field
        update();
    }

    private void addDataEntryField(String labelString, JPanel panel, List<JLabel> labels, List<JTextField> textFields) {
        // Create a new label and text field
        JLabel label = new JLabel(labelString + ":");

        JTextField textField = new JTextField(10);

        //textField.setMaximumSize(new Dimension((int) (textField.getPreferredSize().getWidth()*0.5), textField.getPreferredSize().height));
        //textField.setMaximumSize(new Dimension(200, 50));
        JPanel entryPanel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        entryPanel.add(label, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 0.0;

   
        entryPanel.add(textField, constraints);

        entryPanel.setMaximumSize(new Dimension((int) (entryPanel.getPreferredSize().width*1.1), entryPanel.getPreferredSize().height));

        

        // Add the label and text field to the panel
        // GridBagConstraints labelConstraints = new GridBagConstraints();
        // labelConstraints.gridx = 0;
        // labelConstraints.gridy = index;
        // labelConstraints.anchor = GridBagConstraints.NORTH;
        // labelConstraints.insets = new Insets(5, 5, 0, 5);
        // panel.add(label, labelConstraints, index-1);

        // GridBagConstraints textFieldConstraints = new GridBagConstraints();
        // textFieldConstraints.gridx = 1;
        // textFieldConstraints.gridy = index;
        // textFieldConstraints.anchor = GridBagConstraints.NORTH;
        // textFieldConstraints.insets = new Insets(5, 0, 0, 5);
        // panel.add(textField, textFieldConstraints, index-1);

        //Dimension size = new Dimension((int) (panel.getSize().width*1), entryPanel.getPreferredSize().height);
        //entryPanel.setMaximumSize(entryPanel.getPreferredSize());
        panel.add(entryPanel);

        // Store the label and text field references in lists
        labels.add(label);
        textFields.add(textField);
    }

    private void removeDataEntryField(JPanel panel, List<JLabel> labels, List<JTextField> textFields) {
        // Remove the last label and text field from the panel
        int index = labels.size() - 1;
        panel.remove(labels.get(index));
        panel.remove(textFields.get(index));
        panel.remove(index);

        // Remove the label and text field references from the lists
        labels.remove(index);
        textFields.remove(index);
    }

    private List<Double> getWiderstände() {
        List<Double> entries = new ArrayList<>();
        for (JTextField textField : resistorTextFields) {
            String text = textField.getText();
            try {
                double value = Double.parseDouble(text);
                entries.add(value);
            } catch (NumberFormatException e) {
                // Handle invalid input if necessary
            }
        }
        return entries;
    }

    private List<Double> getSpannungen() {
        List<Double> entries = new ArrayList<>();
        for (JTextField textField : voltageTextFields) {
            String text = textField.getText();
            try {
                double value = Double.parseDouble(text);
                entries.add(value);
            } catch (NumberFormatException e) {
                // Handle invalid input if necessary
            }
        }
        return entries;
    }

    private void calculate() {

        
        try {
            String selectedOPV = model.getSelectedOPV();

        switch(selectedOPV) {
            case "Nichtinvertierer":
                calculateNichtinvertierer();
                break;
            case "Invertierer":
                calculateInvertierer();
                break;
            case "Subtrahierer":
                calculateSubtrahierer();
                break;
            case "Summierer":
                calculateSummierer();
                break;
            default:
            model.setAusgangsspannung(null); 
            model.setVerstärkung(null);
            break;
        }
        } catch (Exception e) {
            String errorMessage = "Fehler bei der Eingabe! \nEs sind nur Zahlen zulässig. \nKommazahlen müssen mit einem Dezimalpunkt geschrieben werden, ein Komma ist nicht zulässig.";
            JOptionPane.showMessageDialog(null, errorMessage, "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateNichtinvertierer() {

            double r_k = getWiderstände().get(1);
            double r_e = getWiderstände().get(0);
            double u_e = getSpannungen().get(0);
            NichtInvertierer opv = new NichtInvertierer(r_k, r_e, u_e);

            HashMap<String, Widerstand> widerstände = new HashMap<String, Widerstand>();
            widerstände.put("R_2", opv.getR_k());
            widerstände.put("R_1", opv.getR_e());

            model.setAusgangsspannung(opv.berechneU_a());
            model.setVerstärkung(opv.berechneVerstärkung());
            model.setWiderstände(widerstände);
    }


    

    private void calculateInvertierer() {

            double r_k = getWiderstände().get(1);
            double r_e = getWiderstände().get(0);
            double u_e = getSpannungen().get(0);
            Invertierer opv = new Invertierer(r_k, r_e, u_e);

            HashMap<String, Widerstand> widerstände = new HashMap<String, Widerstand>();
            widerstände.put("R_1", opv.getR_k());
            widerstände.put("R_1", opv.getR_e());

            model.setAusgangsspannung(opv.berechneU_a());
            model.setVerstärkung(opv.berechneVerstärkung());
            model.setWiderstände(widerstände);

    }

    private void calculateSubtrahierer() {

        double r_k = getWiderstände().get(1);
        double r_e1 = getWiderstände().get(0);
        double r_e2 = getWiderstände().get(2);
        double r_q = getWiderstände().get(3);
        double u_e1 = getSpannungen().get(0);
        double u_e2 = getSpannungen().get(1);

        Subtrahierverstärker opv = new Subtrahierverstärker(r_k, r_q, r_e1, r_e2, u_e1, u_e2);

        HashMap<String, Widerstand> widerstände = new HashMap<String, Widerstand>();
        widerstände.put("R_2", opv.getR_k());
        widerstände.put("R_1", opv.getR_e()[0]);
        widerstände.put("R_3", opv.getR_e()[1]);
        widerstände.put("R_4", opv.getR_q());

        model.setAusgangsspannung(opv.berechneU_a());
        model.setVerstärkung(null);
        model.setWiderstände(widerstände);

    }

    private void calculateSummierer() {
        
        double r_k = getWiderstände().get(0);
        List<Double> r_e_List = getWiderstände();
        r_e_List.remove(0);

        double[] r_e = new double[r_e_List.size()];
        for (int i = 0; i < r_e_List.size(); i++) {
            r_e[i] = r_e_List.get(i);
        }

        List<Double> u_e_List = getSpannungen();
        double[] u_e = new double[u_e_List.size()];
        for (int i = 0; i < u_e_List.size(); i++) {
            u_e[i] = u_e_List.get(i);
        }

        try {
            Summierverstärker opv = new Summierverstärker(r_k, r_e, u_e);
            HashMap<String, Widerstand> widerstände = new HashMap<String, Widerstand>();
            widerstände.put("R_2", opv.getR_k());
            Widerstand[] eingangsWiderstände = opv.getR_e();
            for (int i = 0; i < eingangsWiderstände.length; i++) {
                widerstände.put("R_1" + (i+1), eingangsWiderstände[i]);
            }
    
            model.setAusgangsspannung(opv.berechneU_a());
            model.setVerstärkung(null);
            model.setWiderstände(widerstände);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(addButtonPanel, "Fehler bei der Berechnung!", "Fehler", ERROR);
        }
    }

    @Override
    public void update() {

        // Get the selected opv
        String opv = model.getSelectedOPV();

    
        // Remove all data entry fields
        while (resistorLabels.size() > 0) {
            removeDataEntryField(resistorPanel, resistorLabels, resistorTextFields);
        }
        while (voltageLabels.size() > 0) {
            removeDataEntryField(voltagePanel, voltageLabels, voltageTextFields);
        }

        buttonPanel.remove(addButtonPanel);

        switch(opv) {
            case "Invertierer":
                addDataEntryField("R_1", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("R_2", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("U_e", voltagePanel, voltageLabels, voltageTextFields);
                break;
            case "Nichtinvertierer":
                addDataEntryField("R_1", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("R_2", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("U_e", voltagePanel, voltageLabels, voltageTextFields);
                break;
            case "Subtrahierer":
                addDataEntryField("R_1", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("R_2", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("R_3", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("R_4", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("U_e-", voltagePanel, voltageLabels, voltageTextFields);
                addDataEntryField("U_e+", voltagePanel, voltageLabels, voltageTextFields);
                break;
            case "Summierer":
                addDataEntryField("R_2", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("R_11", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("R_12", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("U_e1", voltagePanel, voltageLabels, voltageTextFields);
                addDataEntryField("U_e2", voltagePanel, voltageLabels, voltageTextFields);
                buttonPanel.add(addButtonPanel, 0);
                break;

        }

        revalidate();
        repaint();
    }
}