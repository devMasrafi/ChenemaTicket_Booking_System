package model;
public class Movie {

    private int movieId;
    private String movieName;
    private String genre;
    private double duration;

    public Movie(int movieId, String movieName, String genre, double duration) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getGenre() {
        return genre;
    }

    public double getDuration() {
        return duration;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void displayMovie() {
        System.out.println("Movie ID: " + movieId);
        System.out.println("Movie Name: " + movieName);
        System.out.println("Genre: " + genre);
        System.out.println("Duration: " + duration + " hours");
    }
}