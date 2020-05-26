package inc.def;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import inc.conn.DBconn;

public class company {
    private int id_company;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;

    public void companyInit() {
        System.out.println(this.getClass().getName());
        Scanner input = new Scanner(System.in);
        System.out.println("Username:");
        username = input.nextLine();
        System.out.println("Password:");
        password = input.nextLine();
        System.out.println("Name:");
        name = input.nextLine();
        System.out.println("E-mail:");
        email = input.nextLine();
        System.out.println("Phone:");
        phone = input.nextLine();
        System.out.println("Address:");
        address = input.nextLine();
    }

    public void insert() {
        System.out.println("Inserting records into the table...");

        try {
            String query = " insert into company (username, password, name, email, phone, address)"
                    + " values (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = DBconn.getConnection().prepareStatement(query);
            preparedStmt.setString (1, username);
            preparedStmt.setString (2, password);
            preparedStmt.setString (3, name);
            preparedStmt.setString (4, email);
            preparedStmt.setString (5, phone);
            preparedStmt.setString (6, address);

            preparedStmt.execute();


        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
