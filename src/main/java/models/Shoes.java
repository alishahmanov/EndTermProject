package models;

public class Shoes extends Products {
    private String brand;
    private String material;
    private String color;
    private String seasonOfShoes;
    private boolean gender;

    public Shoes() {
        super();
    }

    public Shoes(boolean gender, String brand, String material, String seasonOfShoes, String color, int size, int price, String availability) {
        super(size, price, availability);
        setBrand(brand);
        setMaterial(material);
        setSeasonOfShoes(seasonOfShoes);
        setGender(gender);
        setColor(color);
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        if (gender) {
            return "Pair of shoes:\n" + "Gender: Male" + "\nBrand: " + brand + "\nMaterial: " + material + "\nColor: " + color + "\nSeason of shoes: " + seasonOfShoes + "\nSize: " + getSize() + "\nPrice: " + getPrice() + "\nAvailability: " + getAvailability() + "\n";}
        else{
            return "Pair of shoes:\n" + "Gender: Female" + "\nBrand: " + brand + "\nMaterial: " + material + "\nColor: " + color + "\nSeason of shoes: " + seasonOfShoes + "\nSize: " + getSize() + "\nPrice: " + getPrice() + "\nAvailability: " + getAvailability() + "\n";}
    }
}
