package models;

public class Insoles extends Products {
    private String material;
    private String type;

    public Insoles() {
        super();
    }

    public Insoles(String type, String material, int size, int price, String availability) {
        super(size, price, availability);
        setMaterial(material);
        setType(type);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Insoles: \n" + "Type of insoles: "+ type + "\nMaterial: " +  material  + "\nPrice: " +  getPrice() + "\nAvailability: " + getAvailability() + "\n";
    }
}