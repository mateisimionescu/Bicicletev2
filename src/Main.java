public class Main {

    static DBconn database= new DBconn();

    public static void main(String[] args) {
        bike marcel = new bike();
        marcel.bikeInit();
        marcel.insert();
    }
}
