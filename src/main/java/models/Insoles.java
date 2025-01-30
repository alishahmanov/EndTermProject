package models;

import models.enums.Availability;
import models.enums.InsoleType;
import models.enums.Material;

public class Insoles extends Products {
    private Material material;
    private InsoleType type;

    public Insoles() {
        super();
    }

    public Insoles(InsoleType type, Material material, int size, int price, Availability availability) {
        super(size, price, availability);
        setMaterial(material);
        setType(type);
    }


    public InsoleType getType() {
        return type;
    }

    public void setType(InsoleType type) {
        this.type = type;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Insoles: \n" + "Type of insoles: "+ type + "\nMaterial: " +  material  + "\nPrice: " +  getPrice() + "\nAvailability: " + getAvailability() + "\n";
    }
}