package inc.gui;


//import com.mysql.cj.log.Log;
import inc.conn.DBconn;
import inc.conn.Session;
import inc.def.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserGUI extends JFrame{

    static JFrame LoginWindow = new JFrame();
    static GridBagLayout gridBag = new GridBagLayout();
    static GridBagConstraints gbcons = new GridBagConstraints();
    ArrayList<bike> bikes=new ArrayList<bike>();
    ArrayList<bike> ownedBikes=new ArrayList<>();
    JComboBox combo=new JComboBox();
    JComboBox combo2=new JComboBox();
    JLabel inregistrare=new JLabel("Bine ati venit, " + Session.getLoggedIn().getUsername() + "!");
    JLabel rentBike=new JLabel("Alegeti o bicicleta");
    JLabel returnBike=new JLabel("Returnati bicicleta");
    JButton rentBikeButton=new JButton("Inchiriaza");
    JButton returnBikeButton=new JButton("Returneaza");
    JButton history=new JButton("Istoric inchirieri");
    JTable historyTable=new JTable(new DefaultTableModel());
    JTextField test=new JTextField();
    int rentId;
    static void CompAdd(Component comp, int x, int y, int w, int h){
        gbcons.gridx = x;
        gbcons.gridy = y;
        gbcons.gridwidth = w;
        gbcons.gridheight = h;
        gridBag.setConstraints(comp, gbcons);
        LoginWindow.add(comp);
    }




    public UserGUI() {
        ListenForButton listenForButton=new ListenForButton();
        gbcons.insets = new Insets(5, 5, 5, 5);
        LoginWindow.setLayout(gridBag);
        CompAdd(inregistrare,1, 0 ,1 ,1 );
        CompAdd(rentBike,0,1,1,1);
        CompAdd(returnBike,2,1,1,1);
        CompAdd(combo,0,2,1,1);
        CompAdd(combo2,2,2,1,1);
        CompAdd(rentBikeButton,0,4,1,1);
        CompAdd(returnBikeButton,2,4,1,1);
        CompAdd(history,1,5,1,1);





        /*combo.addItem("bikikleta");
        combo.addItem("bicikleta");
        combo.addItem("bikiklet");
        combo.addItem("bikikleta");*/
        getBikes();


        LoginWindow.setSize(new Dimension(500, 500));
        LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginWindow.setVisible(true);

        rentBikeButton.addActionListener(listenForButton);
        returnBikeButton.addActionListener(listenForButton);
        history.addActionListener(listenForButton);


    }

    private void getBikes()
    {
        DBconn connection = new DBconn();
        combo.removeAllItems();
        bikes.clear();
        combo2.removeAllItems();;
        ownedBikes.clear();


        try {
            String query = "SELECT id_bike, id_company, btype, name, price, is_rented FROM bike;";

            PreparedStatement preparedStmt = DBconn.getConnection().prepareStatement(query);


            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                int id_bike=rs.getInt("id_bike");
                int id_company=rs.getInt("id_company");
                String btype=rs.getString("btype");
                String name=rs.getString("name");
                float price=rs.getFloat("price");
                boolean is_rented=rs.getBoolean("is_rented");
                bike b= new bike();
                b.set(id_bike,id_company,btype,name,price,is_rented);
                if(is_rented==false)
                    bikes.add(b);
                else ownedBikes.add(b);


            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        for(bike b:bikes)
        {
            combo.addItem(b.toString());
        }

        for(bike b:ownedBikes)
        {
            combo2.addItem(b.toString());
        }
    }


    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==rentBikeButton)
            {
                //combo.addItem("dsa"+combo.getSelectedIndex());

                try {
                    String query = " update bike set is_rented=true where id_bike = ? ";
                    PreparedStatement preparedStmt = DBconn.getConnection().prepareStatement(query);
                    preparedStmt.setInt (1, bikes.get(combo.getSelectedIndex()).getId());
                    preparedStmt.execute();

                    String query2= " insert into rental(id_user,id_bike,rent_date,rent_hour) values( ?, ?, ?, ?); ";
                    PreparedStatement prepStmt=DBconn.getConnection().prepareStatement(query2);

                    prepStmt.setInt(1,Session.getLoggedIn().getId());
                    prepStmt.setInt(2, bikes.get(combo.getSelectedIndex()).getId());
                    prepStmt.setString(3, "2020-05-22");
                    prepStmt.setString(4,"14:30");

                    prepStmt.execute();






                } catch (SQLException f) {
                    f.printStackTrace();

                }

                getBikes();

            }

            if(e.getSource()==returnBikeButton)
            {
                try {
                    String query = " update bike set is_rented=false where id_bike = ? ";
                    PreparedStatement preparedStmt = DBconn.getConnection().prepareStatement(query);
                    preparedStmt.setInt (1, ownedBikes.get(combo2.getSelectedIndex()).getId());
                    preparedStmt.execute();

                    String query2= " update rental set return_hour='20:00' where id_user= ? and id_bike= ? ";
                    PreparedStatement prepStmt=DBconn.getConnection().prepareStatement(query2);
                    prepStmt.setInt(1,Session.getLoggedIn().getId());
                    prepStmt.setInt(2,ownedBikes.get(combo2.getSelectedIndex()).getId());


                    prepStmt.execute();

                    getBikes();






                } catch (SQLException f) {
                    f.printStackTrace();

                }

            }

            if(e.getSource()==history)
            {
                DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
                model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});
            }



        }
    }



}
