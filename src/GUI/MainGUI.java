
package GUI;
import javax.swing.*;

public class MainGUI {

    public static void main(String[] args) {    
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}