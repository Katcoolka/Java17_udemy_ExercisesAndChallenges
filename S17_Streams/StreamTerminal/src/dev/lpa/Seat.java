package dev.lpa;

import java.util.Random;

public record Seat(char rowMaker, int seatNumber, boolean isReserved) {
    public Seat(char rowMaker, int seatNumber) {
        //this(rowMaker, seatNumber, new Random().nextBoolean()); //for random reservation of seats
        //this(rowMaker, seatNumber, true); //for fully booked event
        this(rowMaker, seatNumber, false); //for no reservations
    }
}
