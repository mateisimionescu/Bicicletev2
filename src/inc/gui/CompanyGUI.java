package inc.gui;


//import com.mysql.cj.log.Log;
import com.mysql.cj.log.Log;
import inc.conn.DBconn;
import inc.conn.Session;
import inc.def.bike;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class CompanyGUI extends JFrame{

    static JFrame LoginWindow = new JFrame();
    static GridBagLayout gridBag = new GridBagLayout();
    private static JPanel MainMenu = new JPanel(gridBag);
    private static JPanel BikeMenu = new JPanel(gridBag);
    private static JPanel ReviewMenu = new JPanel(gridBag);
    private static JPanel BlockMenu = new JPanel(gridBag);
    static GridBagConstraints gbcons = new GridBagConstraints();


    //Main menu
    private JLabel WelcomeLabel=new JLabel("Bine ati venit, " + Session.getLoggedIn().getName() + "!");
    private JLabel MenuLabel=new JLabel("Menu");
    private JButton BikeMenuBtn=new JButton("Bikes");
    private JButton ReviewMenuBtn=new JButton("Review");
    private JButton BlockMenuBtn =new JButton("Block Users");
    private JButton backToMain = new JButton("Main Menu");
    private JButton backToMain2 = new JButton("Main Menu");
    private JButton backToMain3 = new JButton("Main Menu");


    //Bike menu
    private JTable bikeTable = new JTable();
    private JButton addBikeBtn = new JButton("Add");
    private JButton removeBikeBtn = new JButton("Remove");
    JTextField BtypeTXT=new JTextField(30);
    JTextField BnameTXT=new JTextField(30);
    JTextField BpriceTXT=new JTextField(30);
    JLabel lblBtype = new JLabel("Type:");
    JLabel lblBname = new JLabel ("Name (Delete here):");
    JLabel lblBprice = new JLabel ("Price /h");


    static void CompAdd(JPanel tempPanel, Component comp, int x, int y, int w, int h){
        gbcons.gridx = x;
        gbcons.gridy = y;
        gbcons.gridwidth = w;
        gbcons.gridheight = h;
        gridBag.setConstraints(comp, gbcons);
        tempPanel.add(comp);
    }

    private ArrayList<bike>  bikeList() {
        ArrayList<bike> bikeList = new ArrayList<>();

        try {
            String query = "SELECT * FROM bike WHERE id_company = " + Session.getLoggedIn().getId() + ";";

            PreparedStatement preparedStmt = DBconn.getConnection().prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();


            while(rs.next()){
                bike tempBike = new bike();
                tempBike.setId_bike(rs.getInt("id_bike"));
                tempBike.setId_company(rs.getInt("id_company"));
                tempBike.setBtype(rs.getString("btype"));
                tempBike.setName(rs.getString("name"));
                tempBike.setPrice(rs.getFloat("price"));
                tempBike.setIs_rented(rs.getBoolean("is_rented"));
                bikeList.add(tempBike);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bikeList;

    }

    private void ShowBikes(){
        ArrayList<bike> bikes = bikeList();
        bikeTable = new JTable(bikes.size(),6);
        DefaultTableModel model = (DefaultTableModel)bikeTable.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<bikes.size();i++){
            row[0]=bikes.get(i).getId_bike();
            row[1]=bikes.get(i).getId_company();
            row[2]=bikes.get(i).getBtype();
            row[3]=bikes.get(i).getName();
            row[4]=bikes.get(i).getPrice();
            row[5]=bikes.get(i).isIs_rented();
            model.addRow(row);
        }
    }

    private void addBike(){
        bike temp = new bike(Session.getLoggedIn().getId(),BtypeTXT.getText(),BnameTXT.getText(),Float.parseFloat(BpriceTXT.getText()));
        temp.insert();
    }

    private void removeBike(){
        String tempName = BnameTXT.getText();
        try {
            String query = "DELETE FROM bike WHERE name = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = DBconn.getConnection().prepareStatement(query);
            preparedStmt.setString (1, tempName);

            preparedStmt.execute();

            bikeList();
            ShowBikes();


        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    private void AllMenuGenerate()
    {
        //Main Menu
        LoginWindow.add(MainMenu);
        CompAdd(MainMenu,WelcomeLabel,0,0,1,1);
        CompAdd(MainMenu,MenuLabel,0,3,1,1);
        CompAdd(MainMenu, BikeMenuBtn,0,4,1,2);
        CompAdd(MainMenu, ReviewMenuBtn,0,6,1,2);
        CompAdd(MainMenu, BlockMenuBtn,0,8,1,2);
        MainMenu.setVisible(true);

        //Bike Menu

        ShowBikes();
        CompAdd(BikeMenu,addBikeBtn,2,0,3,1);
        CompAdd(BikeMenu,removeBikeBtn,4,0,1,1);

        CompAdd(BikeMenu,lblBtype,2,1,3,1);
        CompAdd(BikeMenu,BtypeTXT,4,1,1,1);

        CompAdd(BikeMenu,lblBname,2,2,3,1);
        CompAdd(BikeMenu,BnameTXT,4,2,1,1);

        CompAdd(BikeMenu,lblBprice,2,3,3,1);
        CompAdd(BikeMenu,BpriceTXT,4,3,1,1);


        CompAdd(BikeMenu,backToMain,3,4,1,1);
        CompAdd(BikeMenu,bikeTable,3,5,1,1);



        //Review Menu
        CompAdd(ReviewMenu,backToMain2,0,0,0,0);

        //Block Menu
        CompAdd(BlockMenu,backToMain3,0,0,0,0);
    }



    public CompanyGUI() {
        gbcons.insets=new Insets(5,5,5,5);
        ListenForButton listenForButton = new ListenForButton();
        AllMenuGenerate();

        gbcons.insets=new Insets(5,5,5,5);
        LoginWindow.setResizable(false);
        LoginWindow.setSize(new Dimension(1000, 600));
        LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginWindow.setVisible(true);

        BikeMenuBtn.addActionListener(listenForButton);
        ReviewMenuBtn.addActionListener(listenForButton);
        BlockMenuBtn.addActionListener(listenForButton);
        backToMain.addActionListener(listenForButton);
        backToMain2.addActionListener(listenForButton);
        backToMain3.addActionListener(listenForButton);
        addBikeBtn.addActionListener(listenForButton);
        removeBikeBtn.addActionListener(listenForButton);
    }

    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(BikeMenuBtn)){
                LoginWindow.add(BikeMenu);
                BikeMenu.setVisible(true);
                MainMenu.setVisible(false);
            }

            if (e.getSource().equals(ReviewMenuBtn)){
                LoginWindow.add(ReviewMenu);
                ReviewMenu.setVisible(true);
                MainMenu.setVisible(false);
            }

            if (e.getSource().equals(BlockMenuBtn)){
                LoginWindow.add(BlockMenu);
                BlockMenu.setVisible(true);
                MainMenu.setVisible(false);
            }

            if (e.getSource().equals(backToMain) || e.getSource().equals(backToMain2) || e.getSource().equals(backToMain3)) {
                LoginWindow.remove(BikeMenu);
                LoginWindow.remove(ReviewMenu);
                LoginWindow.remove(BlockMenu);
                BikeMenu.setVisible(false);
                ReviewMenu.setVisible(false);
                BlockMenu.setVisible(false);
                //LoginWindow.add(MainMenu);
                MainMenu.setVisible(true);
            }

            if (e.getSource().equals(addBikeBtn)){
                addBike();
            }

            if (e.getSource().equals(removeBikeBtn)){
                removeBike();
            }

        }
    }
}
