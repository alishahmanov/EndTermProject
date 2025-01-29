package models;

public abstract class Products {
    private int size;
    private int price;
    private String availability;

    public Products() {
    }

    public Products(int size, int price, String availability) {
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

    public String getAvailability() {
        return availability;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}