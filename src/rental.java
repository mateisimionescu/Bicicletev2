import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import inc.conn.DBconn;

public class rental {
    private String id_rental;
    private int id_user;
    private int id_bike;
    private String rent_date;
    private String rent_hour;
    private String return_hour;


    public void rentalInit() {
        System.out.println(this.getClass().getName());
        Scanner input = new Scanner(System.in);
        System.out.println("id_user:");
        id_user = input.nextInt();
        System.out.println("id_bike:");
        id_bike = input.nextInt();
        System.out.println("Rent date:");
        input.nextLine();
        rent_date = input.nextLine();
        System.out.println("Rent hour:");
        rent_hour = input.nextLine();
        System.out.println("Return hour:");
        return_hour= input.nextLine();
        String rentalname="U"+id_user+"B"+id_bike+"D"+rent_date;
        id_rental=rentalname;

    }

    public void insert() {
        System.out.println("Inserting records into the table...");

        try {
            String query = " insert into rental (id_rental,id_user, id_bike, rent_date, rent_hour, return_hour)"
                    + " values (?,?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = DBconn.getConnection().prepareStatement(query);
            preparedStmt.setString(1,id_rental);
            preparedStmt.setInt (2, id_user);
            preparedStmt.setInt (3, id_bike);
            preparedStmt.setString (4, rent_date);
            preparedStmt.setString (5, rent_hour);
            preparedStmt.setString (6, return_hour);


            preparedStmt.execute();


        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
