package dev.lpa;

public class BookingAgent {

    public static void main(String[] args) {

        int rows = 10;
        int totalSeats = 100;
        Theatre rodgersNYC = new Theatre("Richard Rodgers", rows, totalSeats);
        rodgersNYC.printSeatMap();

        //test cases
        bookSeat(rodgersNYC, 'A', 3);
        bookSeat(rodgersNYC, 'A', 3);
        bookSeat(rodgersNYC, 'B', 1);
        bookSeat(rodgersNYC, 'B', 11);
        bookSeat(rodgersNYC, 'M', 1);

        //test cases for bonus challenge
        bookSeats(rodgersNYC, 4, 'B', 3, 10);
        bookSeats(rodgersNYC, 6, 'B', 'C',3, 10);
        bookSeats(rodgersNYC, 4, 'B', 1, 10);
        bookSeats(rodgersNYC, 4, 'B', 'C',1, 10);
        bookSeats(rodgersNYC, 1, 'B', 'C',1, 10);
        bookSeats(rodgersNYC, 4, 'M', 'Z',1, 10);
        bookSeats(rodgersNYC, 10, 'A', 'E',1, 10);

    }

    private static void bookSeat(Theatre theatre, char row, int seatNo) {
        String seat = theatre.reserveSeat(row, seatNo);
        if (seat != null) {
            System.out.println("Congratulations! Your reserved seat is " + seat);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! Unable to reserve " + row + seatNo);
        }

    }
    //bonus challenge methods
    //overloaded method for booking only a single row
    private static void bookSeats(Theatre theatre, int tickets, char minRow, int minSeat, int maxSeat){
        bookSeats(theatre, tickets, minRow, minRow, minSeat, maxSeat); //if one minRow left out => error
    }
    private static void bookSeats(Theatre theatre, int tickets, char minRow, char maxRow, int minSeat, int maxSeat) {
        var seats = theatre.reserveSeats(tickets, minRow, maxRow, minSeat, maxSeat);
        if (seats != null) {
            System.out.println("Congratulations! Your reserved seats are " + seats);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! No matching contiguous seats in rows: " + minRow + " - " + maxRow);
        }
    }
}