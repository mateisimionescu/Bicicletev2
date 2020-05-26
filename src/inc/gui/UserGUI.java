package inc.gui;


//import com.mysql.cj.log.Log;
import inc.conn.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGUI extends JFrame{

    static JFrame LoginWindow = new JFrame();
    static GridBagLayout gridBag = new GridBagLayout();
    static GridBagConstraints gbcons = new GridBagConstraints();
    static void CompAdd(Component comp, int x, int y, int w, int h){
        gbcons.gridx = x;
        gbcons.gridy = y;
        gbcons.gridwidth = w;
        gbcons.gridheight = h;
        gridBag.setConstraints(comp, gbcons);
        LoginWindow.add(comp);
    }




    public UserGUI() {

        LoginWindow.setSize(new Dimension(500, 500));
        LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginWindow.setVisible(true);
    }

    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
}
