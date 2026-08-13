package GUI;

import model.Movie;
import model.user.Customer;
import service.Cinema;

import javax.swing.*;
import java.awt.*;

public class CustomerDashboard extends JFrame {

        private Customer customer;
        private Cinema cinema;

        private JPanel contentPanel;

        private int getAvailableSeats(model.Show show) {
                int available = 0;
                for (model.Seat seat : show.getSeats()) {

                        if (!seat.isBooked()) {
                                available++;
                        }
                }
                return available;
        }

        public CustomerDashboard(Customer customer, Cinema cinema) {

        this.customer = customer;
        this.cinema = cinema;

        setTitle("Cinema - Customer Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // =========================
        // HEADER
        // =========================

        JLabel welcomeLabel = new JLabel(
                "Welcome, " + customer.getName(),
                SwingConstants.CENTER
        );

        welcomeLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        welcomeLabel.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        );

        mainPanel.add(
                welcomeLabel,
                BorderLayout.NORTH
        );

        // =========================
        // LEFT MENU
        // =========================

        JPanel menuPanel = new JPanel();

        menuPanel.setLayout(
                new GridLayout(5, 1, 10, 10)
        );

        menuPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
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

        mainPanel.add(
                menuPanel,
                BorderLayout.WEST
        );

        // =========================
        // CONTENT AREA
        // =========================

        contentPanel = new JPanel(
                new BorderLayout()
        );

        JLabel welcomeMessage = new JLabel(
                "Select an option from the menu",
                SwingConstants.CENTER
        );

        welcomeMessage.setFont(
                new Font("Arial", Font.PLAIN, 20)
        );

        contentPanel.add(
                welcomeMessage,
                BorderLayout.CENTER
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        // =========================
        // BUTTON ACTIONS
        // =========================

        moviesButton.addActionListener(e -> {
            showMovies();
        });
        showsButton.addActionListener(e -> {
                showShows();
        });

        // Logout
        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame(cinema);
        });

        add(mainPanel);

        setVisible(true);
    }

        // =========================
        // SHOW MOVIES
        // =========================

        private void showMovies() {

        contentPanel.removeAll();

        JLabel title = new JLabel(
                "NOW SHOWING",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        contentPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel moviePanel = new JPanel();

        moviePanel.setLayout(
                new BoxLayout(
                        moviePanel,
                        BoxLayout.Y_AXIS
                )
        );

        if (cinema.getMovies().isEmpty()) {

            JLabel emptyLabel = new JLabel(
                    "No movies available."
            );

            moviePanel.add(emptyLabel);

        } else {

            for (Movie movie : cinema.getMovies()) {

                JPanel movieCard = new JPanel(
                        new GridLayout(4, 1)
                );

                movieCard.setBorder(
                        BorderFactory.createTitledBorder(
                                movie.getMovieName()
                        )
                );

                movieCard.add(
                        new JLabel(
                                "Movie ID: "
                                + movie.getMovieId()
                        )
                );

                movieCard.add(
                        new JLabel(
                                "Genre: "
                                + movie.getGenre()
                        )
                );

                movieCard.add(
                        new JLabel(
                                "Duration: "
                                + movie.getDuration()
                                + " hours"
                        )
                );

                JButton showButton =new JButton("View Shows");
                
                movieCard.add(showButton);

                moviePanel.add(movieCard);
            }
        }

        JScrollPane scrollPane =
                new JScrollPane(moviePanel);

        contentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // Refresh the content panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

        // =========================
        // SHOW SHOWS
        // =========================

        private void showShows() {

        contentPanel.removeAll();

        JLabel title = new JLabel(
            "AVAILABLE SHOWS",
            SwingConstants.CENTER
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 24)
        );

        contentPanel.add(
            title,
            BorderLayout.NORTH
        );

        JPanel showPanel = new JPanel();

        showPanel.setLayout(
            new BoxLayout(
                    showPanel,
                    BoxLayout.Y_AXIS
            )
        );

        if (cinema.getShows().isEmpty()) {

        JLabel emptyLabel = new JLabel(
                "No shows available."
        );

        showPanel.add(emptyLabel);

        } else {

        for (model.Show show : cinema.getShows()) {

            JPanel showCard = new JPanel(
                    new GridLayout(5, 1)
            );

            showCard.setBorder(
                    BorderFactory.createTitledBorder(
                            show.getMovie().getMovieName()
                    )
            );

            showCard.add(
                    new JLabel(
                            "Show ID: "
                            + show.getShowId()
                    )
            );

            showCard.add(
                    new JLabel(
                            "Date: "
                            + show.getDate()
                    )
            );

            showCard.add(
                    new JLabel(
                            "Time: "
                            + show.getTime()
                    )
            );

            showCard.add(
                    new JLabel(
                            "Available Seats: "
                            + getAvailableSeats(show)
                    )
            );

            JButton bookButton =
                    new JButton("Book This Show");

            showCard.add(bookButton);

            showPanel.add(showCard);
        }
        }

        JScrollPane scrollPane =
            new JScrollPane(showPanel);

        contentPanel.add(
            scrollPane,
            BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
}

}