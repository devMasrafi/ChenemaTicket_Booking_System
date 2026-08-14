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

    // =========================
    // TICKETS
    // =========================

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
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