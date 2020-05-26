package inc.gui;


import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    static JFrame LoginWindow;
    static GridBagLayout gridBag;
    static GridBagConstraints gbcons;

    static void CompAdd(Component comp, int x, int y, int w, int h){
        gbcons.gridx = x;
        gbcons.gridy = y;
        gbcons.gridwidth = w;
        gbcons.gridheight = h;
        gridBag.setConstraints(comp, gbcons);
        LoginWindow.add(comp);
    }

    public LoginGUI()
    {
        LoginWindow = new JFrame("Login Bikerino");
        gridBag = new GridBagLayout();
        gbcons = new GridBagConstraints();

        gbcons.weightx = 1.0;
        gbcons.weighty = 1.0;

        gbcons.insets = new Insets(5,5,5,5);
        LoginWindow.setLayout(gridBag);
        JLabel
    }


}
