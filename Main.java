import GUI.LoginFrame;
import service.Cinema;

public class Main {

    public static void main(String[] args) {

        Cinema cinema = new Cinema();

        new LoginFrame(cinema);
    }
}