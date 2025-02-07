package controllers;

import exceptions.SupplierNotFoundException;
import models.Supplier;
import models.enums.Role;
import controllers.interfaces.ISupplierController;
import repositories.interfaces.ISupplierRepository;

import java.util.List;

public class SupplierController implements ISupplierController {
    private final ISupplierRepository repo;

    public SupplierController(ISupplierRepository repo) {
        this.repo = repo;
    }

    @Override
    public String addSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password) {
        if (deliveryCost < 0) {
            return "Delivery cost cannot be negative!";
        }

        // ✅ Исправленный порядок аргументов
        Supplier supplier = new Supplier(null, name, email, password, brandOfShoes, countryOfOrigin, deliveryCost, Role.SUPPLIER);
        boolean created = repo.addSupplier(supplier);

        return created ? "Success" : " Fail";
    }

    @Override
    public String getAllSuppliers() {
        List<Supplier> suppliers = repo.getAllSuppliers();

        if (suppliers.isEmpty()) {
            return "No suppliers found!";
        }

        StringBuilder response = new StringBuilder();
        for (Supplier supplier : suppliers) {
            response.append(supplier).append("\n");
        }

        return response.toString();
    }

    @Override
    public String getSupplierByEmail(String email) {
        return repo.findByEmail(email)
                .map(Supplier::toString)
                .orElseThrow(() -> new SupplierNotFoundException(email));
    }
}
