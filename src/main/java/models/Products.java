package models;
import models.enums.Availability;

public abstract class Products {
    private int size;
    private int price;
    private Availability availability;

    public Products() {
    }

    public Products(int size, int price, Availability availability) {
        setSize(size);
        setPrice(price);
        setAvailability(availability);
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}