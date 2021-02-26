package de.westranger.geometry.common.plot;

public enum Colors {
    RED("red"), GREEN("green"), BLUE("blue"),
    NONE("none");

    private final String color;

    Colors(final String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
