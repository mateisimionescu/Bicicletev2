import java.sql.*;
import java.util.Scanner;
import inc.conn.DBconn;


public class user {
    private int id_user;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;

    public void userInit() {
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
    }

    public void insert() {
        System.out.println("Inserting records into the table...");

        try {
            String query = " insert into user (username, password, name, email, phone)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = DBconn.getConnection().prepareStatement(query);
            preparedStmt.setString (1, username);
            preparedStmt.setString (2, password);
            preparedStmt.setString (3, name);
            preparedStmt.setString (4, email);
            preparedStmt.setString (5, phone);

            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }



    @Override
    public String toString() {
        return "user{" +
                "id_user=" + id_user +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}