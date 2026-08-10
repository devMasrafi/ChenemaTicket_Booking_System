package GUI;

import model.user.Customer;
import service.Cinema;

import javax.swing.*;
import java.awt.*;

public class CustomerLoginFrame extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField phoneField;

    private Cinema cinema;

    public CustomerLoginFrame(Cinema cinema) {
        this.cinema = cinema;

        setTitle("Customer Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel title = new JLabel("CUSTOMER LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        // Customer ID
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Customer ID:"), gbc);

        idField = new JTextField(15);

        gbc.gridx = 1;
        panel.add(idField, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Name:"), gbc);

        nameField = new JTextField(15);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Phone:"), gbc);

        phoneField = new JTextField(15);

        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        panel.add(loginButton, gbc);

        // Login action
        loginButton.addActionListener(e -> login());

        add(panel);

        setVisible(true);
    }

    private void login() {

        try {

            int id = Integer.parseInt(idField.getText());

            String name = nameField.getText();
            String phone = phoneField.getText();

            if (name.isEmpty() || phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields."
                );

                return;
            }

            Customer customer = new Customer(
                    id,
                    name,
                    phone
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Welcome, " + customer.getName() + "!"
            );

            new CustomerDashboard(customer, cinema);

            dispose();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number."
            );
        }
    }
}
