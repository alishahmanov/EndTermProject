package controllers;

import models.Shoes;
import controllers.interfaces.IShoeController;
import repositories.interfaces.IShoesRepository;

import java.util.List;

public class ShoeController implements IShoeController {
    private final IShoesRepository repo;

    public ShoeController(IShoesRepository repo) {
        this.repo = repo;
    }

    public String addShoe(String brand, String material, String color, String seasonOfShoes, boolean gender, int size, int price, String availability) {
        if (price <= 0) {
            return "Price must be greater than 0!";
        }

        if (size <= 0) {
            return "Size must be greater than 0!";
        }

        Shoes shoe = new Shoes(gender, brand, material, seasonOfShoes, color, size, price, availability);
        boolean created = repo.addShoe(shoe);

        return (created ? "Success" : "Fail");
    }

    public String getAllShoes() {
        List<Shoes> shoes = repo.getAllShoes();

        if (shoes.isEmpty()) {
            return "No sneakers found!";
        }

        StringBuilder response = new StringBuilder();
        for (Shoes shoe : shoes) {
            response.append(shoe).append("\n");
        }

        return response.toString();
    }

    public String getShoe(int id) {
        Shoes shoe = repo.getShoe(id);

        return (shoe == null ? "Sneaker was not found" : shoe.toString());
    }
}