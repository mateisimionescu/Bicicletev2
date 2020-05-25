public class Main {

    static DBconn database= new DBconn();

    public static void main(String[] args) {
        user marcel = new user();
        marcel.userInit();
        marcel.insert();
    }
}
