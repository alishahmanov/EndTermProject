package services;
import factorieses.ShoesFactory;
import models.Shoes;
import models.enums.Material;
import models.enums.Season;
import models.enums.Availability;
import models.enums.Role;
import repositories.interfaces.IShoesRepository;
import services.interfaces.IShoesService;

import java.util.List;
import java.util.Optional;

public class ShoesService implements IShoesService {
    private final IShoesRepository repo;

    public ShoesService(IShoesRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Shoes> getShoeById(int id) {
        return repo.getShoe(id);
    }

    @Override
    public String getAllShoes() {
        List<Shoes> shoes = repo.getAllShoes(); // Получаем список обуви из БД

        if (shoes.isEmpty()) return "No sneakers found!";

        StringBuilder response = new StringBuilder("\n List of Available Sneakers:\n");

        for (Shoes shoe : shoes) {
            response.append("────────────────────────────────\n")
                    .append("ID: ").append(shoe.getId()).append("\n")
                    .append("Brand: ").append(shoe.getBrand()).append("\n")
                    .append("Color: ").append(shoe.getColor()).append("\n")
                    .append("Material: ").append(shoe.getMaterial()).append("\n")
                    .append("Season: ").append(shoe.getSeasonOfShoes()).append("\n")
                    .append("Size: ").append(shoe.getSize()).append("\n")
                    .append("Price: $").append(shoe.getPrice()).append("\n")
                    .append("Availability: ").append(shoe.getAvailability()).append("\n");
        }
        response.append("────────────────────────────────\n");

        return response.toString();
    }


    @Override
    public boolean addShoe(String brand, String material, String color, String seasonOfShoes,
                           boolean gender, int size, int price, String availability, Role role) {
        if (role != Role.SUPPLIER) {
            throw new SecurityException("Only SUPPLIERS can add shoes!");
        }

        try {
            Material materialEnum = Material.valueOf(material.toUpperCase());
            Season seasonEnum = Season.valueOf(seasonOfShoes.toUpperCase());
            Availability availabilityEnum = Availability.valueOf(availability.toUpperCase());

            Shoes shoe = ShoesFactory.createShoes(gender, brand, material, seasonOfShoes, color, size, price, availability);
            return repo.addShoe(shoe);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid material, season, or availability type!");
        }
    }
}
