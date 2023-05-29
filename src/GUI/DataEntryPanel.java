package GUI;

import javax.swing.*;

import GUIModel.Model;
import GUIModel.ModelObserver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DataEntryPanel extends JPanel implements ModelObserver {

    private Model model;

    private JPanel entriesPanel;
    private JPanel entriesContainerPanel;

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
    private JButton addButton;
    private JButton removeButton;

    public DataEntryPanel(Model model) {
        this.model = model;
        model.addObserver(this);

        setLayout(new BorderLayout());
        resistorLabels = new ArrayList<>();
        resistorTextFields = new ArrayList<>();

        voltageLabels = new ArrayList<>();
        voltageTextFields = new ArrayList<>();

        // Create the panel to hold the entries
        entriesPanel = new JPanel();
        entriesPanel.setLayout(new GridBagLayout());

        // Create the resistor panel
        resistorPanel = new JPanel();
        resistorPanel.setLayout(new GridBagLayout());

        // Create the resistor scroll pane
        resistorScrollPane = new JScrollPane(resistorPanel);
        resistorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create the voltage panel
        voltagePanel = new JPanel();
        voltagePanel.setLayout(new GridBagLayout());

        // Create the voltage scroll pane
        voltageScrollPane = new JScrollPane(voltagePanel);
        voltageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create the resistor label
        resistorLabel = new JLabel("Widerstände:");

        // Create the voltage label
        voltageLabel = new JLabel("Spannungen:");

        // Set the constraints for vertical alignment
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;

        entriesPanel.add(resistorLabel, gbc);
        gbc.gridy = 1;
        entriesPanel.add(resistorScrollPane, gbc);
        gbc.gridy = 2;
        entriesPanel.add(voltageLabel, gbc);
        gbc.gridy = 3;
        entriesPanel.add(voltageScrollPane, gbc);


        // Create the initial label and text field
        // addDataEntryField("R_2", resistorPanel, resistorLabels, resistorTextFields);
        // addDataEntryField("U_e", voltagePanel, voltageLabels, voltageTextFields);

        update();

        // Wrap the entries panel in a scroll pane
        // JScrollPane scrollPane = new JScrollPane(entriesPanel);
        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the panel
        add(entriesPanel, BorderLayout.CENTER);


        // Create the "Add" button
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataEntryField("R_2", resistorPanel, resistorLabels, resistorTextFields);
                // addDataEntryField("U_e", voltagePanel, voltageLabels, voltageTextFields);
                revalidate();
                repaint();
            }
        });

        // Create the "Remove" button
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // removeDataEntryField();
                revalidate();
                repaint();
            }
        });

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Add the "Add" button to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Add the button panel to the main panel
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addDataEntryField(String labelString, JPanel panel, List<JLabel> labels, List<JTextField> textFields) {
        // Create a new label and text field
        int index = labels.size() + 1;
        JLabel label = new JLabel(labelString + ":");
        JTextField textField = new JTextField(10);

        // Add the label and text field to the panel
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = index;
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        labelConstraints.insets = new Insets(5, 5, 0, 5);
        panel.add(label, labelConstraints);

        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = index;
        textFieldConstraints.anchor = GridBagConstraints.NORTHWEST;
        textFieldConstraints.insets = new Insets(5, 0, 0, 5);
        panel.add(textField, textFieldConstraints);

        // Store the label and text field references in lists
        labels.add(label);
        textFields.add(textField);
    }

    private void removeDataEntryField(JPanel panel, List<JLabel> labels, List<JTextField> textFields) {
        // Remove the last label and text field from the panel
        int index = labels.size() - 1;
        panel.remove(labels.get(index));
        panel.remove(textFields.get(index));

        // Remove the label and text field references from the lists
        labels.remove(index);
        textFields.remove(index);
    }

    public List<Double> getWiderstände() {
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

    public List<Double> getSpannungen() {
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
                addDataEntryField("R_1", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("R_2", resistorPanel, resistorLabels, resistorTextFields);
                addDataEntryField("U_e1", voltagePanel, voltageLabels, voltageTextFields);
                addDataEntryField("U_e2", voltagePanel, voltageLabels, voltageTextFields);
                break;

        }
        revalidate();
        repaint();
    }
}