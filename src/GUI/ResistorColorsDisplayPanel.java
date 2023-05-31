package GUI;

import exceptions.ResistorColorsNotAvailableException;
import widerstand.Widerstand;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.text.DecimalFormat;

/**
 * JPanel zur Darstellung eines Widerstandes mit Farbringen
 * 
 * @author Thomas Wegele, Simon Prießnitz
 */
public class ResistorColorsDisplayPanel extends JPanel {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private JLabel titleLabel;
    private JPanel colorPanel;

    /**
     * Konstruktor des ResistorColorDisplayPanels
     * 
     * @param name       Name des Widerstandes
     * @param widerstand Widerstand, welcher dargestellt werden soll
     * @throws ResistorColorsNotAvailableException Falls der Widerstand keine
     *                                             E-Reihe hat, können keine
     *                                             Farbringe
     */
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
        } catch (Exception e) {
            throw new ResistorColorsNotAvailableException(
                    "Da keine E-Reihe ausgewählt ist können keine Farbringe für die Widerstände erzeugt werden.");
        }

        add(colorPanel, BorderLayout.CENTER);

    }
}
