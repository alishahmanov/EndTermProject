package models;

public class Shoes extends Products {
    private String brand;
    private String material;
    private String color;
    private String seasonOfShoes;
    private boolean gender;

    public Shoes(boolean gender, String brand,  String material, String seasonOfShoes,String color,  int size,  int price, String availability) {
        super(size, price, availability);
        this.price = price;
        this.brand = brand;
        this.material = material;
        this.color = color;
        this.seasonOfShoes = seasonOfShoes;
        this.availability = availability;
        this.gender = gender;

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSeasonOfShoes() {
        return seasonOfShoes;
    }

    public void setSeasonOfShoes(String seasonOfShoes) {
        this.seasonOfShoes = seasonOfShoes;
    }

    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public boolean isGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        if (gender == true) {
            return "Pair of shoes:\n" + "Gender: Male" + "\nBrand: " + brand + "\nMaterial: " + material + "\nColor: " + color + "\nSeason of shoes: " + seasonOfShoes + "\nSize: " + size + "\nPrice: " + price + "\nAvailability: " + availability+ "\n";}
        else{
            return "Pair of shoes:\n" + "Gender: Female" + "\nBrand: " + brand + "\nMaterial: " + material + "\nColor: " + color + "\nSeason of shoes: " + seasonOfShoes + "\nSize: " + size + "\nPrice: " + price + "\nAvailability: " + availability+ "\n";}
    }
}
