package factorieses;

import models.Shoes;
import models.enums.Material;
import models.enums.Season;
import models.enums.Availability;

public class ShoesFactory {
    public static Shoes createShoes(boolean gender, String brand, String material, String seasonOfShoes,
                                    String color, int size, int price, String availability) {
        Material materialEnum = Material.valueOf(material.toUpperCase());
        Season seasonEnum = Season.valueOf(seasonOfShoes.toUpperCase());
        Availability availabilityEnum = Availability.valueOf(availability.toUpperCase());

        return new Shoes(null, gender, brand, materialEnum, seasonEnum, color, size, price, availabilityEnum);
    }
}
