package dev.lpa.model;

public class LPAStudent extends Student {

    private double percentComplete;

    public LPAStudent() {
        percentComplete = random.nextDouble(0, 100.001); //added point 0 0 1, because we do want 100% to be a valid result, and if we truncate it to two decimals, we won't get anything above 100 percent.
    }

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete); //2 percent signs allow to print %
    }

    public double getPercentComplete() {
        return percentComplete;
    }
}
