package services;
import models.enums.*;
import exceptions.ShoeNotFoundException;
import models.Shoes;
import repositories.interfaces.IShoesRepository;
import services.interfaces.IShoesService;

import java.util.List;

public class ShoesService implements IShoesService {
    private final IShoesRepository repo;

    public ShoesService(IShoesRepository repo) {
        this.repo = repo;
    }

    @Override
    public Shoes getShoeById(int id) {
        return repo.getShoe(id)
                .orElseThrow(() -> new ShoeNotFoundException(id));
    }

    @Override
    public List<Shoes> getAllShoes() {
        return repo.getAllShoes();
    }

    @Override
    public boolean addShoe(String brand, String material, String color, String seasonOfShoes, boolean gender, int size, int price, String availability) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0!");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0!");
        }
        try {
            Material materialEnum = Material.valueOf(material.toUpperCase());
            Season seasonEnum = Season.valueOf(seasonOfShoes.toUpperCase());
            Availability availabilityEnum = Availability.valueOf(availability.toUpperCase());

            Shoes shoe = new Shoes(gender, brand, materialEnum, seasonEnum, color, size, price, availabilityEnum);
            return repo.addShoe(shoe);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid material, season, or availability type!");
        }
    }
}
