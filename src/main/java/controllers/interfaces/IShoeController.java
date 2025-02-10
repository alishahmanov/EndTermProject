package controllers.interfaces;

import models.enums.Role;

public interface IShoeController {
    String addShoe(String brand, String material, String color, String seasonOfShoes,
                   boolean gender, int size, int price, String availability, Role role);
    String getAllShoes();
    String getShoe(int id);
}
