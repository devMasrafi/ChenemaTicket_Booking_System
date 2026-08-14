package service;

import java.util.ArrayList;

import model.Movie;
import model.Show;
import model.Ticket;

public class Cinema {

    private ArrayList<Movie> movies;
    private ArrayList<Show> shows;
    private ArrayList<Ticket> tickets;

    public Cinema() {

        movies = new ArrayList<>();
        shows = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    // =========================
    // MOVIES
    // =========================

    public void addMovie(Movie movie) {

        movies.add(movie);
    }

    public void removeMovie(Movie movie) {

        movies.remove(movie);
    }

    public ArrayList<Movie> getMovies() {

        return movies;
    }

    // =========================
    // SHOWS
    // =========================

    public void addShow(Show show) {

        shows.add(show);
    }

    public void removeShow(Show show) {

        shows.remove(show);
    }

    public ArrayList<Show> getShows() {

        return shows;
    }

    // =========================================
    // TICKETS
    // =========================================

    public void addTicket(Ticket ticket) {

        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {

        tickets.remove(ticket);
    }

    public ArrayList<Ticket> getTickets() {

        return tickets;
    }

    // =========================================
    // FIND TICKET
    // =========================================

    public Ticket findTicketById(int ticketId) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {

                return ticket;
            }
        }

        return null;
    }

    // =========================================
    // FIND SHOW
    // =========================================

    public Show findShowById(int showId) {

        for (Show show : shows) {

            if (show.getShowId() == showId) {

                return show;
            }
        }

        return null;
    }

    // =========================================
    // CANCEL INDIVIDUAL TICKET
    // =========================================

    public boolean cancelTicket(int ticketId, String reason) {

        Ticket ticket = findTicketById(ticketId);

        // Ticket doesn't exist
        if (ticket == null) {

            return false;
        }

        // Ticket already cancelled
        if (ticket.getStatus().equals("CANCELLED")) {

            return false;
        }

        ticket.cancelTicket(reason);

        return true;
    }

    // =========================================
    // CANCEL ENTIRE SHOW
    // =========================================

    public boolean cancelShow(int showId, String reason) {

        Show show = findShowById(showId);

        // Show doesn't exist
        if (show == null) {

            return false;
        }

        // Show already cancelled
        if (show.getStatus().equals("CANCELLED")) {

            return false;
        }

        // Cancel the show
        show.cancelShow(reason);

        // Cancel every ticket belonging
        // to this show
        for (Ticket ticket : tickets) {

            if (ticket.getShow() == show
                    && !ticket.getStatus().equals("CANCELLED")) {

                ticket.cancelTicket(reason);
            }
        }

        return true;
    }

    // =========================
    // CONSOLE METHODS
    // =========================

    public void displayMovies() {

        System.out.println("\n===== AVAILABLE MOVIES =====");

        if (movies.isEmpty()) {

            System.out.println("No movies available.");

            return;
        }

        for (Movie movie : movies) {

            movie.displayMovie();

            System.out.println("--------------------");
        }
    }

    public void displayShows() {

        System.out.println("\n===== AVAILABLE SHOWS =====");

        if (shows.isEmpty()) {

            System.out.println("No shows available.");

            return;
        }

        for (Show show : shows) {

            show.displayShow();

            System.out.println("--------------------");
        }
    }
}