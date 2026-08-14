package model;

public class Seat {

    private String seatNumber;
    private boolean booked;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.booked = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void bookSeat() {
        booked = true;
    }

    public void cancelSeat() {
        booked = false;
    }

    public void displaySeat() {

        if (booked) {
            System.out.println(seatNumber + " - BOOKED");
        } else {
            System.out.println(seatNumber + " - AVAILABLE");
        }
    }
}