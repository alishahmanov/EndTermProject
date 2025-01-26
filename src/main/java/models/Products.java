package models;

public abstract class Products {
    private int size;
    private int price;
    private String availability;

    public Products() {
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public String getAvailability() {
        return availability;
    }

    public Products(int size, int price, String availability) {
        this.size = size;
        this.price = price;
        this.availability = availability;
    }
}
