package models;
import models.enums.Material;
import models.enums.Season;
import models.enums.Availability;

public class Shoes extends Products {
    private String brand;
    private Material material;
    private String color;
    private Season seasonOfShoes;
    private boolean gender;

    public Shoes() {
        super();
    }

    public Shoes(boolean gender, String brand, Material material, Season seasonOfShoes, String color, int size, int price, Availability availability) {
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Season getSeasonOfShoes() {
        return seasonOfShoes;
    }

    public void setSeasonOfShoes(Season seasonOfShoes) {
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
