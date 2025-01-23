package models;

public class Insoles extends Products {
    private String material;
    private String type;

    public Insoles(String type, String material, int size, int price, String availability) {
        super(size, price,availability);
        this.material = material;
        this.type = type;
    }
    @Override
    public String toString() {
        return "Insoles: \n" + "Type of insoles: "+ type + "\nMaterial: " +  material  + "\nPrice: " + price + "\nAvailability: " + availability+ "\n";
    }
}
