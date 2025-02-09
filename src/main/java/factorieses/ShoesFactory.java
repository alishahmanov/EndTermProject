package factorieses;

import models.Shoes;
import models.enums.Material;
import models.enums.Season;
import models.enums.Availability;

public class ShoesFactory {
    public static Shoes createShoes(boolean gender, String brand, String material, String seasonOfShoes,
                                    String color, int size, int price, String availability) {
        // ✅ Преобразуем строки в ENUM
        Material materialEnum = Material.valueOf(material.toUpperCase());
        Season seasonEnum = Season.valueOf(seasonOfShoes.toUpperCase());
        Availability availabilityEnum = Availability.valueOf(availability.toUpperCase());

        // ✅ Создаём объект Shoes с ID = null (если он создаётся в БД автоматически)
        return new Shoes(null, gender, brand, materialEnum, seasonEnum, color, size, price, availabilityEnum);
    }
}
