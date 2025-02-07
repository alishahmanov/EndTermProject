package services;
import models.enums.*;
import exceptions.ShoeNotFoundException;
import models.Shoes;
import repositories.interfaces.IShoesRepository;
import services.interfaces.IShoesService;

import java.util.List;
import java.util.stream.Stream;

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
        return repo.getAllShoes().stream()
                .filter(shoe -> shoe.getAvailability() == Availability.IN_STOCK)
                .toList();
    }


    @Override
    public boolean addShoe(String brand, String material, String color, String seasonOfShoes, boolean gender, int size, int price, String availability) {
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0!");
        if (size <= 0) throw new IllegalArgumentException("Size must be greater than 0!");

        try {
            Material materialEnum = Stream.of(Material.values())
                    .filter(m -> m.name().equalsIgnoreCase(material))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid material type!"));

            Season seasonEnum = Stream.of(Season.values())
                    .filter(s -> s.name().equalsIgnoreCase(seasonOfShoes))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid season type!"));

            Availability availabilityEnum = Stream.of(Availability.values())
                    .filter(a -> a.name().equalsIgnoreCase(availability))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid availability type!"));

            return repo.addShoe(new Shoes(gender, brand, materialEnum, seasonEnum, color, size, price, availabilityEnum));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid material, season, or availability type!");
        }
    }

}
