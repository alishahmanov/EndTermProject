package controllers;
import exceptions.ShoeNotFoundException;
import models.Shoes;
import controllers.interfaces.IShoeController;
import repositories.interfaces.IShoesRepository;
import models.enums.*;

import java.util.List;

public class ShoeController implements IShoeController {
    private final IShoesRepository repo;

    public ShoeController(IShoesRepository repo) {
        this.repo = repo;
    }

    @Override
    public String addShoe(String brand, String material, String color, String seasonOfShoes,
                          boolean gender, int size, int price, String availability) {
        try {
            Material matEnum = Material.valueOf(material.toUpperCase());
            Season seasonEnum = Season.valueOf(seasonOfShoes.toUpperCase());
            Availability availEnum = Availability.valueOf(availability.toUpperCase());

            return addShoe(brand, matEnum, color, seasonEnum, gender, size, price, availEnum);
        } catch (IllegalArgumentException e) {
            return "Invalid material, season, or availability type.";
        }
    }

    public String addShoe(String brand, Material material, String color, Season seasonOfShoes,
                          boolean gender, int size, int price, Availability availability) {
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

    @Override
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

    @Override
    public String getShoe(int id) {
        return repo.getShoe(id)
                .map(Shoes::toString)
                .orElseThrow(() -> new ShoeNotFoundException(id));
    }
}
