package services;

import exceptions.InsoleNotFoundException;
import models.Insoles;
import models.Shoes;
import models.enums.Availability;
import models.enums.InsoleType;
import models.enums.Material;
import repositories.interfaces.IInsolesRepository;
import services.interfaces.IInsolesService;

import java.util.List;

public class InsolesService implements IInsolesService {
    private final IInsolesRepository repo;

    public InsolesService(IInsolesRepository repo) {
        this.repo = repo;
    }

    @Override
    public Insoles getInsoleById(int id) {
        return repo.getInsoles(id)
                .orElseThrow(() -> new InsoleNotFoundException(id));
    }

    @Override
    public List<Insoles> getAllInsoles() {
        return repo.getAllInsoles();
    }

    @Override
    public boolean addInsole(String type, String material, int size, int price, String availability) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0!");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0!");
        }
        try {
            Material materialEnum = Material.valueOf(material.toUpperCase());
            InsoleType typeEnum = InsoleType.valueOf(type.toUpperCase());
            Availability availabilityEnum = Availability.valueOf(availability.toUpperCase());
            Insoles insole = new Insoles(typeEnum, materialEnum, size, price, availabilityEnum);
            return repo.addInsoles(insole);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid material, season, or availability type!");
        }
    }
}
