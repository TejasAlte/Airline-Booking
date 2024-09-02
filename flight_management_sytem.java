import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationSystem {

    // Flight class
    static class Flight {
        private String flightNumber;
        private String origin;
        private String destination;
        private String departureTime;
        private int availableSeats;

        public Flight(String flightNumber, String origin, String destination, String departureTime, int availableSeats) {
            this.flightNumber = flightNumber;
            this.origin = origin;
            this.destination = destination;
            this.departureTime = departureTime;
            this.availableSeats = availableSeats;
        }

        public String getFlightNumber() { return flightNumber; }
        public String getOrigin() { return origin; }
        public String getDestination() { return destination; }
        public String getDepartureTime() { return departureTime; }
        public int getAvailableSeats() { return availableSeats; }

        public boolean bookSeat() {
            if (availableSeats > 0) {
                availableSeats--;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Flight{" +
                    "flightNumber='" + flightNumber + '\'' +
                    ", origin='" + origin + '\'' +
                    ", destination='" + destination + '\'' +
                    ", departureTime='" + departureTime + '\'' +
                    ", availableSeats=" + availableSeats +
                    '}';
        }
    }

    // Passenger class
    static class Passenger {
        private String name;
        private String passportNumber;

        public Passenger(String name, String passportNumber) {
            this.name = name;
            this.passportNumber = passportNumber;
        }

        public String getName() { return name; }
        public String getPassportNumber() { return passportNumber; }

        @Override
        public String toString() {
            return "Passenger{" +
                    "name='" + name + '\'' +
                    ", passportNumber='" + passportNumber + '\'' +
                    '}';
        }
    }

    // Reservation class
    static class Reservation {
        private Flight flight;
        private Passenger passenger;

        public Reservation(Flight flight, Passenger passenger) {
            this.flight = flight;
            this.passenger = passenger;
        }

        public Flight getFlight() { return flight; }
        public Passenger getPassenger() { return passenger; }

        @Override
        public String toString() {
            return "Reservation{" +
                    "flight=" + flight +
                    ", passenger=" + passenger +
                    '}';
        }
    }

    // AirlineReservationSystem class
    private List<Flight> flights = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void makeReservation(String flightNumber, Passenger passenger) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber) && flight.bookSeat()) {
                Reservation reservation = new Reservation(flight, passenger);
                reservations.add(reservation);
                System.out.println("Reservation successful!");
                return;
            }
        }
        System.out.println("Reservation failed. Flight not found or no available seats.");
    }

    public void viewReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static void main(String[] args) {
        AirlineReservationSystem system = new AirlineReservationSystem();

        // Add some flights
        system.addFlight(new Flight("AI101", "New York", "Los Angeles", "10:00 AM", 2));
        system.addFlight(new Flight("AI102", "Boston", "San Francisco", "11:00 AM", 3));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. View reservations");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter flight number: ");
                    String flightNumber = scanner.nextLine();
                    System.out.print("Enter passenger name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter passport number: ");
                    String passportNumber = scanner.nextLine();
                    system.makeReservation(flightNumber, new Passenger(name, passportNumber));
                    break;
                case 2:
                    system.viewReservations();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
