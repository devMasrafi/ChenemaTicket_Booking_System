package model.user;

public class Admin extends User {

    public Admin(int userId, String name, String phone) {
        super(userId, name, phone);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n===== ADMIN MENU =====");
        System.out.println("1. Add Movie");
        System.out.println("2. Remove Movie");
        System.out.println("3. View Movies");
        System.out.println("4. Add Show");
        System.out.println("5. View Shows");
        System.out.println("6. View Bookings");
        System.out.println("7. Logout");
    }
}