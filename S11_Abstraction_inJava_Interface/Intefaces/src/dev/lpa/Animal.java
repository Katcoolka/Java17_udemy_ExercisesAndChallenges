package dev.lpa;

import java.util.IllegalFormatCodePointException;

enum FlightStages implements Trackable {
    GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;


    @Override
    public void track() {
        if (this != GROUNDED) {
            System.out.println("Monitoring " + this);
        }
    }

    public FlightStages getNextStage() {
        //array for all enums, even if they change in the future
        FlightStages[] allStages = values();
        return allStages[(ordinal() + 1) % allStages.length];
    }
}

record DragonFly(String name, String type) implements FlightEnabled {

    @Override
    public void takeOff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void fly() {

    }
}

class Satellite implements OrbitEarth {
    FlightStages stage = FlightStages.GROUNDED;

    public void achieveOrbit() {
        transition("Orbit achieved");
    }

    @Override
    public void takeOff() {
        transition("Taking off");
    }

    @Override
    public void land() {
        transition("landing");
    }

    @Override
    public void fly() {
        achieveOrbit();
        transition("Data collection while Orbiting");
    }

    public void transition(String description) {
        System.out.println(description);
        stage = transition(stage);
        stage.track();
    }
}

interface OrbitEarth extends FlightEnabled {
    void achieveOrbit();

    private static void log(String description) {
        var today = new java.util.Date();
        System.out.println(today + ": " + description);
    }

    private void logStage(FlightStages stage, String description) {
        description = stage + ": " + description;
        log(description);
    }

    @Override
    default FlightStages transition(FlightStages stage) {
        FlightStages nextStage = FlightEnabled.super.transition(stage);
        logStage(stage, "Beginning transition to " + nextStage);
        return nextStage;
    }
}

interface FlightEnabled {
    //constants - it can't be changed
    //public static final are redundant
    double MILES_TO_KM = 1.60934;
    double KM_TO_MILES = 0.621371;

    //listed methods without redundant info like public and abstract
    void takeOff();

    void land();

    void fly();

    default FlightStages transition(FlightStages stage) {
//         System.out.println("transition not implemented on " + getClass().getName());
//         return null;
        FlightStages nextStage = stage.getNextStage();
        System.out.println("Transitioning from " + stage + " to " + nextStage);
        return nextStage;
    }
}

interface Trackable {
    void track();
}

public abstract class Animal {
    public abstract void move();
}
