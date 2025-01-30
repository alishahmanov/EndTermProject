package models.enums;

public enum Season {
    WINTER("Winter"),
    SUMMER("Summer"),
    SPRING("Spring"),
    AUTUMN("Autumn"),
    ALL_SEASONS("All Seasons"),;

    private final String displayName;

    Season(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
