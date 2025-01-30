package controllers;

import exceptions.InsoleNotFoundException;
import models.Insoles;
import controllers.interfaces.IInsoleController;
import repositories.interfaces.IInsolesRepository;

import java.util.List;

public class InsoleController implements IInsoleController {
    private final IInsolesRepository repo;

    public InsoleController(IInsolesRepository repo) {
        this.repo = repo;
    }

    public String addInsole(String type, String material, int size, int price, String availability) {
        if (price <= 0) {
            return "Price must be greater than 0!";
        }

        if (size <= 0) {
            return "Size must be greater than 0!";
        }

        Insoles insole = new Insoles(type, material, size, price, availability);
        boolean created = repo.addInsoles(insole);

        return (created ? "Success" : "Fail");
    }

    public String getAllInsoles() {
        List<Insoles> insoles = repo.getAllInsoles();

        if (insoles.isEmpty()) {
            return "No insoles found!";
        }

        StringBuilder response = new StringBuilder();
        for (Insoles insole : insoles) {
            response.append(insole).append("\n");
        }

        return response.toString();
    }

    public String getInsole(int id) {
        Insoles insole = repo.getInsoles(id);
        if (insole == null) {
            throw new InsoleNotFoundException(id);
        }
        return insole.toString();
    }
}
