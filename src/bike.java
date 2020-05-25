import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class bike {
    private int id_bike;
    private int id_company;
    private String type;
    private String name;
    private float price;

    public void bikeInit() {
        System.out.println(this.getClass().getName());
        Scanner input = new Scanner(System.in);
        System.out.println("Type:");
        type = input.nextLine();
        System.out.println("Companie:");
        id_company = input.nextInt();
        System.out.println("Name:");
        name = input.nextLine();
        System.out.println("Price:");
        price = input.nextInt();
    }

    public void insert() {
        System.out.println("Inserting records into the table...");

        try {
            String query = " insert into company (id_company, type, name, price)"
                    + " values (?, ?, ?, ?)";

            PreparedStatement preparedStmt = DBconn.getConnection().prepareStatement(query);
            preparedStmt.setInt (1, id_company);
            preparedStmt.setString (2, type);
            preparedStmt.setString (3, name);
            preparedStmt.setFloat (4, price);

            preparedStmt.execute();


        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
