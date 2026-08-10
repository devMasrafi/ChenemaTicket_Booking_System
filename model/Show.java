package model;
import java.util.ArrayList;

public class Show {

    private int showId;
    private Movie movie;
    private String date;
    private String time;
    private ArrayList<Seat> seats;

    public Show(int showId, Movie movie, String date, String time) {

        this.showId = showId;
        this.movie = movie;
        this.date = date;
        this.time = time;

        seats = new ArrayList<>();

        // Create 20 seats
        for (int i = 1; i <= 20; i++) {
            seats.add(new Seat("A" + i));
        }
    }

    public int getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void displayShow() {

        System.out.println("Show ID: " + showId);
        System.out.println("Movie: " + movie.getMovieName());
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);

        System.out.println("\nSeats:");

        for (Seat seat : seats) {
            seat.displaySeat();
        }
    }
}