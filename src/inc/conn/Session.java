package inc.conn;

import inc.def.*;

public class Session {
    static Object loggedIn;
    //buna ziua
    Session(){}

    public static Object getLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(user loggedIn) {
        Session.loggedIn = loggedIn;
    }

    public static void setLoggedIn(company loggedIn) {
        Session.loggedIn = loggedIn;
    }
}
