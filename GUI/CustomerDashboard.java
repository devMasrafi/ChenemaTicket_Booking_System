package GUI;

import model.Movie;
import model.Seat;
import model.Show;
import model.Ticket;
import model.user.Customer;
import service.Cinema;

import javax.swing.*;
import java.awt.*;

public class CustomerDashboard extends JFrame {

    private Customer customer;
    private Cinema cinema;

    private JPanel contentPanel;

    private int nextTicketId = 1001;

    public CustomerDashboard(Customer customer, Cinema cinema) {

        this.customer = customer;
        this.cinema = cinema;

        setTitle("Cinema - Customer Dashboard");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(new BorderLayout());

        // =========================
        // HEADER
        // =========================

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, " + customer.getName(),
                        SwingConstants.CENTER
                );

        welcomeLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        welcomeLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 10, 15, 10
                )
        );

        mainPanel.add(
                welcomeLabel,
                BorderLayout.NORTH
        );

        // =========================
        // LEFT MENU
        // =========================

        JPanel menuPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                10,
                                10
                        )
                );

        menuPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JButton moviesButton =
                new JButton("View Movies");

        JButton showsButton =
                new JButton("View Shows");

        JButton ticketsButton =
                new JButton("My Tickets");

        JButton logoutButton =
                new JButton("Logout");

        menuPanel.add(moviesButton);
        menuPanel.add(showsButton);
        menuPanel.add(ticketsButton);
        menuPanel.add(logoutButton);

        mainPanel.add(
                menuPanel,
                BorderLayout.WEST
        );

        // =========================
        // CONTENT PANEL
        // =========================

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        JLabel welcomeMessage =
                new JLabel(
                        "Select an option from the menu",
                        SwingConstants.CENTER
                );

        welcomeMessage.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        20
                )
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

        ticketsButton.addActionListener(e -> {

            showMyTickets();

        });

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame(cinema);

        });

        add(mainPanel);

        setVisible(true);
    }

    // =====================================================
    // MOVIES
    // =====================================================

    private void showMovies() {

        contentPanel.removeAll();

        JLabel title =
                new JLabel(
                        "NOW SHOWING",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        contentPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel moviePanel =
                new JPanel();

        moviePanel.setLayout(
                new BoxLayout(
                        moviePanel,
                        BoxLayout.Y_AXIS
                )
        );

        if (cinema.getMovies().isEmpty()) {

            moviePanel.add(
                    new JLabel(
                            "No movies available."
                    )
            );

        } else {

            for (Movie movie :
                    cinema.getMovies()) {

                JPanel movieCard =
                        new JPanel(
                                new GridLayout(
                                        4,
                                        1
                                )
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

                JButton showButton =
                        new JButton(
                                "View Shows"
                        );

                showButton.addActionListener(e -> {

                    showShows();

                });

                movieCard.add(showButton);

                moviePanel.add(movieCard);
            }
        }

        JScrollPane scrollPane =
                new JScrollPane(
                        moviePanel
                );

        contentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // =====================================================
    // SHOWS
    // =====================================================

    private void showShows() {

        contentPanel.removeAll();

        JLabel title =
                new JLabel(
                        "AVAILABLE SHOWS",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        contentPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel showPanel =
                new JPanel();

        showPanel.setLayout(
                new BoxLayout(
                        showPanel,
                        BoxLayout.Y_AXIS
                )
        );

        if (cinema.getShows().isEmpty()) {

            showPanel.add(
                    new JLabel(
                            "No shows available."
                    )
            );

        } else {

            for (Show show :
                    cinema.getShows()) {

                JPanel showCard =
                        new JPanel(
                                new GridLayout(
                                        6,
                                        1
                                )
                        );

                showCard.setBorder(
                        BorderFactory.createTitledBorder(
                                show.getMovie()
                                        .getMovieName()
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

                JLabel statusLabel =
                        new JLabel(
                                "Status: "
                                        + show.getStatus()
                        );

                showCard.add(statusLabel);

                // =========================
                // CANCELLED SHOW
                // =========================

                if (show.getStatus()
                        .equals("CANCELLED")) {

                    JLabel reasonLabel =
                            new JLabel(
                                    "Reason: "
                                            + show
                                            .getCancellationReason()
                            );

                    showCard.add(reasonLabel);

                } else {

                    JButton bookButton =
                            new JButton(
                                    "Book This Show"
                            );

                    bookButton.addActionListener(e -> {

                        showSeatSelection(show);

                    });

                    showCard.add(bookButton);
                }

                showPanel.add(showCard);
            }
        }

        JScrollPane scrollPane =
                new JScrollPane(
                        showPanel
                );

        contentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // =====================================================
    // AVAILABLE SEATS
    // =====================================================

    private int getAvailableSeats(Show show) {

        int available = 0;

        for (Seat seat :
                show.getSeats()) {

            if (!seat.isBooked()) {

                available++;
            }
        }

        return available;
    }

    // =====================================================
    // SEAT SELECTION
    // =====================================================

    private void showSeatSelection(Show show) {

        // =========================
        // EXTRA SAFETY CHECK
        // =========================

        if (show.getStatus()
                .equals("CANCELLED")) {

            JOptionPane.showMessageDialog(
                    this,
                    "This show has been cancelled.\n\n"
                            + "Reason: "
                            + show.getCancellationReason()
            );

            showShows();

            return;
        }

        contentPanel.removeAll();

        JLabel title =
                new JLabel(
                        "SELECT YOUR SEAT - "
                                + show.getMovie()
                                .getMovieName(),
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        contentPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel seatPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                5,
                                10,
                                10
                        )
                );

        ButtonGroup seatGroup =
                new ButtonGroup();

        for (Seat seat :
                show.getSeats()) {

            JRadioButton seatButton =
                    new JRadioButton(
                            seat.getSeatNumber()
                    );

            seatButton.setHorizontalAlignment(
                    SwingConstants.CENTER
            );

            seatButton.setActionCommand(
                    seat.getSeatNumber()
            );

            if (seat.isBooked()) {

                seatButton.setEnabled(false);
            }

            seatGroup.add(seatButton);

            seatPanel.add(seatButton);
        }

        JScrollPane scrollPane =
                new JScrollPane(
                        seatPanel
                );

        contentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =========================
        // BOTTOM
        // =========================

        JPanel bottomPanel =
                new JPanel();

        JButton confirmButton =
                new JButton(
                        "Confirm Booking"
                );

        JButton backButton =
                new JButton(
                        "Back to Shows"
                );

        bottomPanel.add(confirmButton);
        bottomPanel.add(backButton);

        contentPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // BACK
        // =========================

        backButton.addActionListener(e -> {

            showShows();

        });

        // =========================
        // CONFIRM BOOKING
        // =========================

        confirmButton.addActionListener(e -> {

            // Check again if show was cancelled
            if (show.getStatus()
                    .equals("CANCELLED")) {

                JOptionPane.showMessageDialog(
                        this,
                        "This show has been cancelled.\n\n"
                                + "Reason: "
                                + show
                                .getCancellationReason()
                );

                showShows();

                return;
            }

            ButtonModel selectedModel =
                    seatGroup.getSelection();

            if (selectedModel == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a seat."
                );

                return;
            }

            String selectedSeatNumber =
                    selectedModel.getActionCommand();

            Seat selectedSeat = null;

            for (Seat seat :
                    show.getSeats()) {

                if (seat.getSeatNumber()
                        .equals(
                                selectedSeatNumber
                        )) {

                    selectedSeat = seat;

                    break;
                }
            }

            if (selectedSeat == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seat could not be found."
                );

                return;
            }

            // =========================
            // CHECK SEAT
            // =========================

            if (selectedSeat.isBooked()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sorry, this seat is already booked."
                );

                showSeatSelection(show);

                return;
            }

            // =========================
            // BOOK SEAT
            // =========================

            selectedSeat.bookSeat();

            // =========================
            // CREATE TICKET
            // =========================

            Ticket ticket =
                    new Ticket(
                            nextTicketId++,
                            customer,
                            show,
                            selectedSeat,
                            500.0
                    );

            // =========================
            // STORE TICKET
            // =========================

            cinema.addTicket(ticket);

            JOptionPane.showMessageDialog(
                    this,
                    "BOOKING SUCCESSFUL!\n\n"
                            + "Ticket ID: "
                            + ticket.getTicketId()
                            + "\nMovie: "
                            + show.getMovie()
                            .getMovieName()
                            + "\nDate: "
                            + show.getDate()
                            + "\nTime: "
                            + show.getTime()
                            + "\nSeat: "
                            + selectedSeat
                            .getSeatNumber()
                            + "\nPrice: "
                            + ticket.getPrice()
            );

            showShows();
        });

        refreshContent();
    }

    // =====================================================
    // MY TICKETS
    // =====================================================

    private void showMyTickets() {

        contentPanel.removeAll();

        JLabel title =
                new JLabel(
                        "MY TICKETS",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        contentPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel ticketPanel =
                new JPanel();

        ticketPanel.setLayout(
                new BoxLayout(
                        ticketPanel,
                        BoxLayout.Y_AXIS
                )
        );

        boolean found = false;

        for (Ticket ticket :
                cinema.getTickets()) {

            if (ticket.getCustomer()
                    .getUserId()
                    == customer.getUserId()) {

                found = true;

                JPanel ticketCard =
                        new JPanel(
                                new GridLayout(
                                        8,
                                        1
                                )
                        );

                ticketCard.setBorder(
                        BorderFactory.createTitledBorder(
                                "Ticket #"
                                        + ticket
                                        .getTicketId()
                        )
                );

                ticketCard.add(
                        new JLabel(
                                "Movie: "
                                        + ticket
                                        .getShow()
                                        .getMovie()
                                        .getMovieName()
                        )
                );

                ticketCard.add(
                        new JLabel(
                                "Date: "
                                        + ticket
                                        .getShow()
                                        .getDate()
                        )
                );

                ticketCard.add(
                        new JLabel(
                                "Time: "
                                        + ticket
                                        .getShow()
                                        .getTime()
                        )
                );

                ticketCard.add(
                        new JLabel(
                                "Seat: "
                                        + ticket
                                        .getSeat()
                                        .getSeatNumber()
                        )
                );

                ticketCard.add(
                        new JLabel(
                                "Price: "
                                        + ticket.getPrice()
                        )
                );

                // =========================
                // STATUS
                // =========================

                JLabel statusLabel =
                        new JLabel(
                                "Status: "
                                        + ticket.getStatus()
                        );

                ticketCard.add(statusLabel);

                // =========================
                // CANCELLATION REASON
                // =========================

                if (ticket.getStatus()
                        .equals("CANCELLED")) {

                    ticketCard.add(
                            new JLabel(
                                    "Cancellation Reason: "
                                            + ticket
                                            .getCancellationReason()
                            )
                    );

                    ticketCard.add(
                            new JLabel(
                                    "This ticket is no longer valid."
                            )
                    );

                } else {

                    JButton cancelButton =
                            new JButton(
                                    "Cancel Ticket"
                            );

                    cancelButton.addActionListener(e -> {

                        cancelCustomerTicket(ticket);

                    });

                    ticketCard.add(
                            cancelButton
                    );
                }

                ticketPanel.add(ticketCard);
            }
        }

        if (!found) {

            ticketPanel.add(
                    new JLabel(
                            "You have no tickets."
                    )
            );
        }

        JScrollPane scrollPane =
                new JScrollPane(
                        ticketPanel
                );

        contentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // =====================================================
    // CUSTOMER CANCEL TICKET
    // =====================================================

    private void cancelCustomerTicket(
            Ticket ticket) {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Cancel Ticket #"
                                + ticket
                                .getTicketId()
                                + "?\n\n"
                                + "Movie: "
                                + ticket
                                .getShow()
                                .getMovie()
                                .getMovieName()
                                + "\nSeat: "
                                + ticket
                                .getSeat()
                                .getSeatNumber(),
                        "Cancel Ticket",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {

            return;
        }

        String reason =
                JOptionPane.showInputDialog(
                        this,
                        "Please enter a cancellation reason:"
                );

        if (reason == null
                || reason.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cancellation reason is required."
            );

            return;
        }

        boolean cancelled =
                cinema.cancelTicket(
                        ticket.getTicketId(),
                        reason.trim()
                );

        if (cancelled) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ticket cancelled successfully.\n"
                            + "The seat is now available."
            );

            showMyTickets();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to cancel this ticket."
            );
        }
    }

    // =====================================================
    // REFRESH
    // =====================================================

    private void refreshContent() {

        contentPanel.revalidate();

        contentPanel.repaint();
    }
}