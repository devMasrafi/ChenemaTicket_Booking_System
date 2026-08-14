package GUI;

import model.user.Admin;
import service.Cinema;

import javax.swing.*;
import java.awt.*;

public class AdminLoginFrame extends JFrame {

    private Cinema cinema;

    private JTextField idField;
    private JTextField nameField;
    private JTextField phoneField;

    public AdminLoginFrame(Cinema cinema) {

        this.cinema = cinema;

        setTitle("Admin Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        JLabel title =
                new JLabel("ADMIN LOGIN");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(title, gbc);

        gbc.gridwidth = 1;

        // ID
        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(
                new JLabel("Admin ID:"),
                gbc
        );

        idField =
                new JTextField(15);

        gbc.gridx = 1;

        panel.add(idField, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                new JLabel("Name:"),
                gbc
        );

        nameField =
                new JTextField(15);

        gbc.gridx = 1;

        panel.add(nameField, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(
                new JLabel("Phone:"),
                gbc
        );

        phoneField =
                new JTextField(15);

        gbc.gridx = 1;

        panel.add(phoneField, gbc);

        // Login
        JButton loginButton =
                new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            login();
        });

        add(panel);

        setVisible(true);
    }

    private void login() {

        try {

            int id =
                    Integer.parseInt(
                            idField.getText().trim()
                    );

            String name =
                    nameField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            if (name.isEmpty()
                    || phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields."
                );

                return;
            }

            Admin admin =
                    new Admin(
                            id,
                            name,
                            phone
                    );

            new AdminDashboard(
                    admin,
                    cinema
            );

            dispose();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Admin ID must be a number."
            );
        }
    }
}