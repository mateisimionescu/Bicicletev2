package inc.gui;



import com.mysql.cj.log.Log;
import inc.conn.DBconn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterU extends JFrame {


    static JFrame LoginWindow = new JFrame("Login Bikerino");
    static GridBagLayout gridBag = new GridBagLayout();
    static GridBagConstraints gbcons = new GridBagConstraints();


    JLabel lblLogin = new JLabel("Bikerino", JLabel.CENTER);
    JLabel lblNume = new JLabel("Username:");
    JLabel lblParola = new JLabel("Password:");
    JTextField txtUser = new JTextField(30);
    JTextField txtPass = new JPasswordField(30);
    JButton btnLoginC = new JButton("Company Login");
    JButton btnLoginU = new JButton("User Login");
    JButton btnRegisterU = new JButton("User Register");


    static void CompAdd(Component comp, int x, int y, int w, int h) {
        gbcons.gridx = x;
        gbcons.gridy = y;
        gbcons.gridwidth = w;
        gbcons.gridheight = h;
        gridBag.setConstraints(comp, gbcons);
        LoginWindow.add(comp);
    }


    public RegisterU() {

        LoginWindow.setSize(new Dimension(500, 500));
        LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginWindow.setVisible(true);
        //LoginGUI.ListenForButton lForButton = new LoginGUI.ListenForButton();
        gbcons.weightx = 0.5;
        gbcons.weighty = 0.5;
        gbcons.insets = new Insets(5, 5, 5, 5);
        LoginWindow.setLayout(gridBag);

        lblLogin.setFont(new Font("Arial", Font.BOLD, 24));
        CompAdd(lblLogin, 0, 0, 4, 2);

        CompAdd(lblNume, 0, 2, 1, 1);
        CompAdd(lblParola, 0, 3, 1, 1);
        CompAdd(txtUser, 1, 2, 2, 1);
        CompAdd(txtPass, 1, 3, 2, 1);

    }
}