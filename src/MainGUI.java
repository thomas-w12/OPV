import GUI.MainFrame;

import javax.swing.SwingUtilities;

/**
 * Hauptklasse der Anwendung
 * @author Thomas Wegele
 */
public class MainGUI {

    /**
     * Main-Methode der Anwendung, öffnet das Fenster
     * @param args
     */
    public static void main(String[] args) {    
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}