package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import widerstand.Widerstand;

public class ResistorColorsDisplayPanel extends JPanel {

    private static final DecimalFormat df = new DecimalFormat("0.00");


    private JLabel titleLabel;
    private JPanel colorPanel;

    public ResistorColorsDisplayPanel(String name, Widerstand widerstand) throws ResistorColorsNotAvailableException {

        setLayout(new BorderLayout());

        titleLabel = new JLabel(name + " = " + df.format(widerstand.getWiderstandswert()) + " Ω");

        add(titleLabel, BorderLayout.NORTH);

        colorPanel = new JPanel();

        colorPanel.setLayout(new FlowLayout());
        
        try {
            for (Color color : widerstand.getFarbringe()) {
                JPanel colorRectangle = new JPanel();
                colorRectangle.setPreferredSize(new Dimension(20, 50));
                colorRectangle.setBackground(color);
                colorPanel.add(colorRectangle);
            }
        } catch(Exception e) {
            throw new ResistorColorsNotAvailableException("Da keine E-Reihe ausgewählt ist können keine Farbringe für die Widerstände erzeugt werden.");
        }
        

        add(colorPanel, BorderLayout.CENTER);

    }
}

class ResistorColorsNotAvailableException extends Exception {
    public ResistorColorsNotAvailableException(String message) {
        super(message);
    }
}