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

    // Add Movie
    public void addMovie(Movie movie) {
        movies.add(movie);
        System.out.println("Movie added successfully!");
    }

    // Add Show
    public void addShow(Show show) {
        shows.add(show);
        System.out.println("Show added successfully!");
    }

    // Add Ticket
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    // Display all movies
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

    // Display all shows
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

    // Getters
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}