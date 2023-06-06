package dev.lpa.model;

public class LPAStudent extends Student {

    private double percentComplete;

    public LPAStudent() {
        percentComplete = random.nextDouble(0, 100.001);
    }

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete); //2 percent signs allow to print %
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchfieldValue(String fieldName, String value) {
        if (fieldName.equalsIgnoreCase("percentComplete")){
            return percentComplete <= Integer.parseInt(value);
        }
        return super.matchfieldValue(fieldName, value);
    }
}
