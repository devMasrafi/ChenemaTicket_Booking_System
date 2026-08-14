package GUI;

import model.Movie;
import model.Show;
import model.Ticket;
import model.user.Admin;
import service.Cinema;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

  private Admin admin;
  private Cinema cinema;

  private JPanel contentPanel;

  public AdminDashboard(Admin admin, Cinema cinema) {

    this.admin = admin;
    this.cinema = cinema;

    setTitle("Cinema - Admin Dashboard");
    setSize(1000, 650);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel mainPanel = new JPanel(new BorderLayout());

    // =========================
    // HEADER
    // =========================

    JLabel header = new JLabel("ADMIN DASHBOARD - " + admin.getName(), SwingConstants.CENTER);

    header.setFont(new Font("Arial", Font.BOLD, 24));

    header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

    mainPanel.add(header, BorderLayout.NORTH);

    // =========================
    // MENU
    // =========================

    JPanel menu = new JPanel(new GridLayout(9, 1, 10, 10));

    menu.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    JButton addMovieButton = new JButton("Add Movie");

    JButton removeMovieButton = new JButton("Remove Movie");

    JButton moviesButton = new JButton("View Movies");

    JButton addShowButton = new JButton("Add Show");

    JButton showsButton = new JButton("View Shows");

    JButton bookingsButton = new JButton("View Bookings");

    JButton cancelTicketButton = new JButton("Cancel Ticket");

    JButton cancelShowButton = new JButton("Cancel Show");

    JButton logoutButton = new JButton("Logout");

    menu.add(addMovieButton);
    menu.add(removeMovieButton);
    menu.add(moviesButton);
    menu.add(addShowButton);
    menu.add(showsButton);
    menu.add(bookingsButton);
    menu.add(cancelTicketButton);
    menu.add(cancelShowButton);
    menu.add(logoutButton);

    mainPanel.add(menu, BorderLayout.WEST);

    // =========================
    // CONTENT
    // =========================

    contentPanel = new JPanel(new BorderLayout());

    JLabel welcome = new JLabel("Select an admin option", SwingConstants.CENTER);

    welcome.setFont(new Font("Arial", Font.PLAIN, 20));

    contentPanel.add(welcome, BorderLayout.CENTER);

    mainPanel.add(contentPanel, BorderLayout.CENTER);

    // =========================
    // BUTTONS
    // =========================

    addMovieButton.addActionListener(e -> {
      addMovie();
    });

    removeMovieButton.addActionListener(e -> {
      removeMovie();
    });

    moviesButton.addActionListener(e -> {
      showMovies();
    });

    addShowButton.addActionListener(e -> {
      addShow();
    });

    showsButton.addActionListener(e -> {
      showShows();
    });

    bookingsButton.addActionListener(e -> {
      showBookings();
    });

    cancelTicketButton.addActionListener(e -> {
      cancelTicket();
    });

    cancelShowButton.addActionListener(e -> {
      cancelShow();
    });

    logoutButton.addActionListener(e -> {

      dispose();

      new LoginFrame(cinema);
    });

    add(mainPanel);

    setVisible(true);
  }

  // =====================================================
  // ADD MOVIE
  // =====================================================

  private void addMovie() {

    JTextField idField = new JTextField();

    JTextField nameField = new JTextField();

    JTextField genreField = new JTextField();

    JTextField durationField = new JTextField();

    JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

    panel.add(new JLabel("Movie ID:"));
    panel.add(idField);

    panel.add(new JLabel("Movie Name:"));
    panel.add(nameField);

    panel.add(new JLabel("Genre:"));
    panel.add(genreField);

    panel.add(new JLabel("Duration:"));
    panel.add(durationField);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Movie", JOptionPane.OK_CANCEL_OPTION);

    if (result != JOptionPane.OK_OPTION) {

      return;
    }

    try {

      int id = Integer.parseInt(idField.getText().trim());

      String name = nameField.getText().trim();

      String genre = genreField.getText().trim();

      double duration = Double.parseDouble(durationField.getText().trim());

      if (name.isEmpty() || genre.isEmpty()) {

        JOptionPane.showMessageDialog(this, "Please fill all fields.");

        return;
      }

      Movie movie = new Movie(id, name, genre, duration);

      cinema.addMovie(movie);

      JOptionPane.showMessageDialog(this, "Movie added successfully!");

    } catch (NumberFormatException ex) {

      JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
    }
  }

  // =====================================================
  // REMOVE MOVIE
  // =====================================================

  private void removeMovie() {

    if (cinema.getMovies().isEmpty()) {

      JOptionPane.showMessageDialog(this, "No movies available.");

      return;
    }

    String[] movieNames = new String[cinema.getMovies().size()];

    for (int i = 0; i < cinema.getMovies().size(); i++) {

      movieNames[i] = cinema.getMovies().get(i).getMovieName();
    }

    String selected = (String) JOptionPane.showInputDialog(this, "Select movie to remove:", "Remove Movie",
        JOptionPane.QUESTION_MESSAGE, null, movieNames, movieNames[0]);

    if (selected == null) {
      return;
    }

    for (Movie movie : cinema.getMovies()) {

      if (movie.getMovieName().equals(selected)) {

        cinema.removeMovie(movie);

        JOptionPane.showMessageDialog(this, "Movie removed successfully!");

        return;
      }
    }
  }

  // =====================================================
  // VIEW MOVIES
  // =====================================================

  private void showMovies() {

    contentPanel.removeAll();

    JLabel title = new JLabel("ALL MOVIES", SwingConstants.CENTER);

    title.setFont(new Font("Arial", Font.BOLD, 24));

    contentPanel.add(title, BorderLayout.NORTH);

    JPanel panel = new JPanel();

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    for (Movie movie : cinema.getMovies()) {

      panel.add(new JLabel("ID: " + movie.getMovieId() + " | " + movie.getMovieName() + " | " + movie.getGenre() + " | "
          + movie.getDuration() + " hours"));
    }

    contentPanel.add(new JScrollPane(panel), BorderLayout.CENTER);

    refresh();
  }

  // =====================================================
  // ADD SHOW
  // =====================================================

  private void addShow() {

    if (cinema.getMovies().isEmpty()) {

      JOptionPane.showMessageDialog(this, "Add a movie first.");

      return;
    }

    JTextField idField = new JTextField();

    JTextField dateField = new JTextField();

    JTextField timeField = new JTextField();

    String[] movieNames = new String[cinema.getMovies().size()];

    for (int i = 0; i < cinema.getMovies().size(); i++) {

      movieNames[i] = cinema.getMovies().get(i).getMovieName();
    }

    JComboBox<String> movieBox = new JComboBox<>(movieNames);

    JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

    panel.add(new JLabel("Show ID:"));
    panel.add(idField);

    panel.add(new JLabel("Movie:"));
    panel.add(movieBox);

    panel.add(new JLabel("Date:"));
    panel.add(dateField);

    panel.add(new JLabel("Time:"));
    panel.add(timeField);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Show", JOptionPane.OK_CANCEL_OPTION);

    if (result != JOptionPane.OK_OPTION) {

      return;
    }

    try {

      int id = Integer.parseInt(idField.getText().trim());

      String date = dateField.getText().trim();

      String time = timeField.getText().trim();

      Movie selectedMovie = cinema.getMovies().get(movieBox.getSelectedIndex());

      Show show = new Show(id, selectedMovie, date, time);

      cinema.addShow(show);

      JOptionPane.showMessageDialog(this, "Show added successfully!\n" + "20 seats created.");

    } catch (NumberFormatException ex) {

      JOptionPane.showMessageDialog(this, "Show ID must be a number.");
    }
  }

  // =====================================================
  // VIEW SHOWS
  // =====================================================

  private void showShows() {

    contentPanel.removeAll();

    JLabel title = new JLabel("ALL SHOWS", SwingConstants.CENTER);

    title.setFont(new Font("Arial", Font.BOLD, 24));

    contentPanel.add(title, BorderLayout.NORTH);

    JPanel panel = new JPanel();

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    for (Show show : cinema.getShows()) {

      int available = 0;

      for (model.Seat seat : show.getSeats()) {

        if (!seat.isBooked()) {
          available++;
        }
      }

      String showText = "Show ID: " + show.getShowId() + " | Movie: " + show.getMovie().getMovieName() + " | Date: "
          + show.getDate() + " | Time: " + show.getTime() + " | Available: " + available + " | Status: "
          + show.getStatus();

      if (show.getStatus().equals("CANCELLED")) {

        showText += " | Reason: " + show.getCancellationReason();
      }

      panel.add(new JLabel(showText));
    }

    contentPanel.add(new JScrollPane(panel), BorderLayout.CENTER);

    refresh();
  }

  // =====================================================
  // BOOKINGS
  // =====================================================

  private void showBookings() {

    contentPanel.removeAll();

    JLabel title = new JLabel("ALL BOOKINGS", SwingConstants.CENTER);

    title.setFont(new Font("Arial", Font.BOLD, 24));

    contentPanel.add(title, BorderLayout.NORTH);

    JPanel panel = new JPanel();

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    if (cinema.getTickets().isEmpty()) {

      panel.add(new JLabel("No bookings yet."));

    } else {

      for (Ticket ticket : cinema.getTickets()) {

        String ticketText = "Ticket #" + ticket.getTicketId() + " | Customer: " + ticket.getCustomer().getName()
            + " | Movie: " + ticket.getShow().getMovie().getMovieName() + " | Seat: " + ticket.getSeat().getSeatNumber()
            + " | Price: " + ticket.getPrice() + " | Status: " + ticket.getStatus();

        if (ticket.getStatus().equals("CANCELLED")) {

          ticketText += " | Reason: " + ticket.getCancellationReason();
        }

        panel.add(new JLabel(ticketText));
      }
    }

    contentPanel.add(new JScrollPane(panel), BorderLayout.CENTER);

    refresh();
  }

  // =====================================================
  // CANCEL TICKET
  // =====================================================

  private void cancelTicket() {

    if (cinema.getTickets().isEmpty()) {

      JOptionPane.showMessageDialog(this, "There are no tickets to cancel.");

      return;
    }

    JTextField ticketIdField = new JTextField();

    JTextField reasonField = new JTextField();

    JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

    panel.add(new JLabel("Ticket ID:"));

    panel.add(ticketIdField);

    panel.add(new JLabel("Reason:"));

    panel.add(reasonField);

    int result = JOptionPane.showConfirmDialog(this, panel, "Cancel Ticket", JOptionPane.OK_CANCEL_OPTION);

    if (result != JOptionPane.OK_OPTION) {

      return;
    }

    try {

      int ticketId = Integer.parseInt(ticketIdField.getText().trim());

      String reason = reasonField.getText().trim();

      if (reason.isEmpty()) {

        JOptionPane.showMessageDialog(this, "Please enter a cancellation reason.");

        return;
      }

      Ticket ticket = cinema.findTicketById(ticketId);

      if (ticket == null) {

        JOptionPane.showMessageDialog(this, "Ticket not found.");

        return;
      }

      if (ticket.getStatus().equals("CANCELLED")) {

        JOptionPane.showMessageDialog(this, "This ticket is already cancelled.");

        return;
      }

      int confirmation = JOptionPane.showConfirmDialog(this,
          "Cancel Ticket #" + ticketId + "?\n\n" + "Customer: " + ticket.getCustomer().getName() + "\nMovie: "
              + ticket.getShow().getMovie().getMovieName() + "\nSeat: " + ticket.getSeat().getSeatNumber()
              + "\n\nReason: " + reason,
          "Confirm Cancellation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

      if (confirmation != JOptionPane.YES_OPTION) {

        return;
      }

      boolean cancelled = cinema.cancelTicket(ticketId, reason);

      if (cancelled) {

        JOptionPane.showMessageDialog(this,
            "Ticket #" + ticketId + " cancelled successfully.\n" + "The seat is now available.");

        showBookings();

      } else {

        JOptionPane.showMessageDialog(this, "Unable to cancel ticket.");
      }

    } catch (NumberFormatException ex) {

      JOptionPane.showMessageDialog(this, "Ticket ID must be a number.");
    }
  }

  // =====================================================
  // CANCEL SHOW
  // =====================================================

  private void cancelShow() {

    if (cinema.getShows().isEmpty()) {

      JOptionPane.showMessageDialog(this, "There are no shows to cancel.");

      return;
    }

    JTextField showIdField = new JTextField();

    JTextField reasonField = new JTextField();

    JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

    panel.add(new JLabel("Show ID:"));

    panel.add(showIdField);

    panel.add(new JLabel("Reason:"));

    panel.add(reasonField);

    int result = JOptionPane.showConfirmDialog(this, panel, "Cancel Show", JOptionPane.OK_CANCEL_OPTION);

    if (result != JOptionPane.OK_OPTION) {

      return;
    }

    try {

      int showId = Integer.parseInt(showIdField.getText().trim());

      String reason = reasonField.getText().trim();

      if (reason.isEmpty()) {

        JOptionPane.showMessageDialog(this, "Please enter a cancellation reason.");

        return;
      }

      Show show = cinema.findShowById(showId);

      if (show == null) {

        JOptionPane.showMessageDialog(this, "Show not found.");

        return;
      }

      if (show.getStatus().equals("CANCELLED")) {

        JOptionPane.showMessageDialog(this, "This show is already cancelled.");

        return;
      }

      // Count affected tickets
      int affectedTickets = 0;

      for (Ticket ticket : cinema.getTickets()) {

        if (ticket.getShow() == show && !ticket.getStatus().equals("CANCELLED")) {

          affectedTickets++;
        }
      }

      int confirmation = JOptionPane.showConfirmDialog(this,
          "Cancel this show?\n\n" + "Movie: " + show.getMovie().getMovieName() + "\nDate: " + show.getDate()
              + "\nTime: " + show.getTime() + "\n\n" + affectedTickets + " ticket(s) will also be cancelled."
              + "\n\nReason: " + reason,
          "Confirm Show Cancellation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

      if (confirmation != JOptionPane.YES_OPTION) {

        return;
      }

      boolean cancelled = cinema.cancelShow(showId, reason);

      if (cancelled) {

        JOptionPane.showMessageDialog(this,
            "Show #" + showId + " cancelled successfully.\n" + affectedTickets + " ticket(s) cancelled.");

        showShows();

      } else {

        JOptionPane.showMessageDialog(this, "Unable to cancel show.");
      }

    } catch (NumberFormatException ex) {

      JOptionPane.showMessageDialog(this, "Show ID must be a number.");
    }
  }

  // =====================================================
  // REFRESH
  // =====================================================

  private void refresh() {

    contentPanel.revalidate();
    contentPanel.repaint();
  }
}