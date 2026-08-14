package GUI;

import service.Cinema;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private Cinema cinema;

    public LoginFrame(Cinema cinema) {

        this.cinema = cinema;

        setTitle("Cinema Ticket Booking System");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel(
                        "CINEMA TICKET SYSTEM"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(title, gbc);

        // =========================
        // WELCOME
        // =========================

        JLabel welcome =
                new JLabel(
                        "Welcome to our Cinema"
                );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        gbc.gridy = 1;

        panel.add(welcome, gbc);

        // =========================
        // CUSTOMER
        // =========================

        JButton customerButton =
                new JButton("Customer Login");

        customerButton.setPreferredSize(
                new Dimension(220, 45)
        );

        gbc.gridy = 2;

        panel.add(customerButton, gbc);

        customerButton.addActionListener(e -> {

            new CustomerLoginFrame(cinema);

            dispose();
        });

        // =========================
        // ADMIN
        // =========================

        JButton adminButton =
                new JButton("Admin Login");

        adminButton.setPreferredSize(
                new Dimension(220, 45)
        );

        gbc.gridy = 3;

        panel.add(adminButton, gbc);

        adminButton.addActionListener(e -> {

            new AdminLoginFrame(cinema);

            dispose();
        });

        // =========================
        // EXIT
        // =========================

        JButton exitButton =
                new JButton("Exit");

        exitButton.setPreferredSize(
                new Dimension(220, 45)
        );

        gbc.gridy = 4;

        panel.add(exitButton, gbc);

        exitButton.addActionListener(e -> {

            System.exit(0);
        });

        add(panel);

        setVisible(true);
    }
}