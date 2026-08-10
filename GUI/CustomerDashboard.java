package GUI;

import model.user.Customer;
import service.Cinema;

import javax.swing.*;
import java.awt.*;

public class CustomerDashboard extends JFrame {

    private Customer customer;
    private Cinema cinema;

    public CustomerDashboard(Customer customer, Cinema cinema) {

        this.customer = customer;
        this.cinema = cinema;

        setTitle("Cinema - Customer Dashboard");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header
        JLabel welcomeLabel = new JLabel(
                "Welcome, " + customer.getName(),
                SwingConstants.CENTER
        );

        welcomeLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(
                new GridLayout(5, 1, 10, 10)
        );

        JButton moviesButton = new JButton("View Movies");
        JButton showsButton = new JButton("View Shows");
        JButton bookingButton = new JButton("Book Ticket");
        JButton ticketsButton = new JButton("My Tickets");
        JButton logoutButton = new JButton("Logout");

        menuPanel.add(moviesButton);
        menuPanel.add(showsButton);
        menuPanel.add(bookingButton);
        menuPanel.add(ticketsButton);
        menuPanel.add(logoutButton);

        mainPanel.add(menuPanel, BorderLayout.WEST);

        // Center message
        JLabel message = new JLabel(
                "Select an option from the menu",
                SwingConstants.CENTER
        );

        message.setFont(
                new Font("Arial", Font.PLAIN, 20)
        );

        mainPanel.add(message, BorderLayout.CENTER);

        // Logout
        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame(cinema);
        });

        add(mainPanel);

        setVisible(true);
    }
}