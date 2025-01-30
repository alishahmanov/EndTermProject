package models.enums;

public enum Availability {
    IN_STOCK("In Stock"),
    OUT_OF_STOCK("Our Stock"),
    IN_DELIVERY("In Delivery");

    private final String displayName;

    Availability(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
