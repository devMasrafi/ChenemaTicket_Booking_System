package model;

import model.user.Customer;

public class Ticket {

    private int ticketId;
    private Customer customer;
    private Show show;
    private Seat seat;
    private double price;

    // Ticket status
    private String status;

    // Cancellation information
    private String cancellationReason;

    public Ticket(int ticketId, Customer customer, Show show, Seat seat, double price) {

        this.ticketId = ticketId;
        this.customer = customer;
        this.show = show;
        this.seat = seat;
        this.price = price;

        // New tickets are confirmed
        this.status = "CONFIRMED";
        this.cancellationReason = "";
    }

    public int getTicketId() {
        return ticketId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Show getShow() {
        return show;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    // =========================================
    // CANCEL TICKET
    // =========================================

    public void cancelTicket(String reason) {

        // Don't cancel an already cancelled ticket
        if (status.equals("CANCELLED")) {
            return;
        }

        status = "CANCELLED";
        cancellationReason = reason;

        // Release the seat
        seat.cancelSeat();
    }

    // =========================================
    // DISPLAY TICKET
    // =========================================

    public void displayTicket() {

        System.out.println("\n===== TICKET =====");

        System.out.println("Ticket ID: " + ticketId);

        System.out.println("Customer: " + customer.getName());

        System.out.println("Movie: " + show.getMovie().getMovieName());

        System.out.println("Date: " + show.getDate());

        System.out.println("Time: " + show.getTime());

        System.out.println("Seat: " + seat.getSeatNumber());

        System.out.println("Price: " + price);

        System.out.println("Status: " + status);

        if (status.equals("CANCELLED")) {

            System.out.println("Cancellation Reason: " + cancellationReason);
        }

        System.out.println("==================");
    }
}