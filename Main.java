import GUI.LoginFrame;
import model.Movie;
import service.Cinema;
import model.Show;

public class Main {

    public static void main(String[] args) {

        Cinema cinema = new Cinema();

        // Movies
        Movie movie1 = new Movie(101,"Avengers","Action",2.5);

        Movie movie2 = new Movie(102, "Inception","Sci-Fi",2.8
        );

        Movie movie3 = new Movie(103,"Interstellar","Sci-Fi",2.9
        );

        
        // shows 
        Show show1 = new Show(201,movie1,"15-08-2026","6:00 PM"
        );

        Show show2 = new Show(202,movie1,"15-08-2026","9:00 PM"
        );

        Show show3 = new Show(203,movie2,"16-08-2026","7:00 PM"
        );

        Show show4 = new Show(204,movie3,"16-08-2026","9:30 PM"
        );
        
        cinema.addMovie(movie1);
        cinema.addMovie(movie2);
        cinema.addMovie(movie3);

        cinema.addShow(show1);
        cinema.addShow(show2);
        cinema.addShow(show3);
        cinema.addShow(show4);

        new LoginFrame(cinema);
    }
}