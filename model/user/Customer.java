package model.user;

public class Customer extends User {

    public Customer(int userId, String name, String phone) {
        super(userId, name, phone);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n===== CUSTOMER MENU =====");
        System.out.println("1. View Movies");
        System.out.println("2. View Shows");
        System.out.println("3. Book Ticket");
        System.out.println("4. Cancel Ticket");
        System.out.println("5. View My Tickets");
        System.out.println("6. Logout");
    }
}