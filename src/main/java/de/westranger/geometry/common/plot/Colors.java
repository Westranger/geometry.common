package de.westranger.geometry.common.plot;

/**
 * @see <a href="https://colorswall.com/palette/73">Microsoft 10 core colors palette</a>
 */

public enum Colors {
    RED("#e81123"), GREEN("#009e49"), BLUE("#00188f"),
    YELLOW("#fff100"), ORANGE("#ff8c00"), MAGENTA("#ec008c"),
    PURPLE("#68217a"), CYAN("#00bcf2"), TEAL("#00b294"),
    LIME("#bad80a"), WHITE("#ffffff"), BLACK("#000000"), NONE("none");

    //TODO ein grau hinzufügen, das zu den andren Farben passt

    private final String color;

    Colors(final String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
