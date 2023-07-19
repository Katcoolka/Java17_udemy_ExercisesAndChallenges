package dev.lpa;

import java.time.LocalDate;

public enum Generation {

    //If you thought about GEN Z as a class => inserting a line of code in a class body does not work, but we can wrap it in another code block.
    GEN_Z {
        {
            System.out.println("--SPECIAL FOR " + this + " --");
        }
    },
    MILLENNIAL(1981, 2000),
    GEN_X(1965, 1980),
    BABY_BOOMER(1945, 1964),
    SILENT_GENERATION(1927, 1945),
    GREATEST_GENERATION(1901, 1926); //semicolon has to be added after enums, if we are adding more code

    private final int startYear;
    private final int endYear;

    //initial constructor
    //    Generation() {
//        System.out.println(this);
//    }
    Generation() {
        this(2001, LocalDate.now().getYear());
    }

    //after creating this constructor enums must be updated with arguments in parentheses
    Generation(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.name() + " " + startYear + " - " + endYear;
    }
}
