package inc.def;

public abstract class Account {
    protected String username;
    protected String password;
    protected String name;
    protected String email;
    protected String phone;

    public String getUsername() {
        return username;
    }

    public abstract int getId();

    public String getName() {
        return name;
    }
}
