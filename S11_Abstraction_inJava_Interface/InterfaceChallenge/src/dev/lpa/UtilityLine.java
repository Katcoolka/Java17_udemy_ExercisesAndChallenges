package dev.lpa;

enum UtilityType {ELECTRICAL, FIBER_OPTIC, GAS, WATER}

public class UtilityLine implements Mappable {
    private String name;
    private UtilityType type;

    public UtilityLine(String name, UtilityType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getLabel() {
        return name + " (" + type + ") ";
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

    @Override
    public String getMarker() {
        return switch (type) {
            case ELECTRICAL -> Color.RED + " " + LineMaker.DASHED;
            case FIBER_OPTIC -> Color.GREEN + " " + LineMaker.DOTTED;
            case GAS -> Color.ORANGE + " " + LineMaker.SOLID;
            case WATER -> Color.BLUE + " " + LineMaker.SOLID;
            default -> Color.BLACK + " " + LineMaker.SOLID;
        };
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + """
                , "name": "%s", "utility": "%s" """.formatted(name, type);
    }
}
