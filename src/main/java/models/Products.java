package models;

public abstract class Products {
    int size;
    int price;
    String availability;

    public Products(int size, int price, String availability) {
        this.size = size;
        this.price = price;
        this.availability = availability;
    }
}
