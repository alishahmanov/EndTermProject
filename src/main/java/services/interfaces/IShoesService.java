package services.interfaces;

import models.Shoes;
import models.enums.Role;

import java.util.List;
import java.util.Optional;

public interface IShoesService {
    Optional<Shoes> getShoeById(int id);
    String getAllShoes(); // ✅ Теперь возвращает String
    boolean addShoe(String brand, String material, String color, String seasonOfShoes,
                    boolean gender, int size, int price, String availability, Role role);
}
