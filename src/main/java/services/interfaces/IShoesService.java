package services.interfaces;

import models.Shoes;
import java.util.List;

public interface IShoesService {
    Shoes getShoeById(int id);
    List<Shoes> getAllShoes();
    boolean addShoe(String brand, String material, String color, String seasonOfShoes, boolean gender, int size, int price, String availability);
}
