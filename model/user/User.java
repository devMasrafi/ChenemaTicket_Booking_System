package model.user;

public abstract class User {

    protected int userId;
    protected String name;
    protected String phone;

    public User(int userId, String name, String phone) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public abstract void displayMenu();
}
