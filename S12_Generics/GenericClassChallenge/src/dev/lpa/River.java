package dev.lpa;

public class River extends Line {
    private String name;
    //in the constructor, instead of passing a single location string, I want to allow multiple location strings using a variable argument(...), and call that locations.
    public River(String name, String... locations) {
        super(locations);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " River";
    }
}
