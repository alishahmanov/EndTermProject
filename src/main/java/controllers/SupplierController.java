package controllers;

import models.Supplier;
import controllers.interfaces.ISupplierController;
import repositories.interfaces.ISupplierRepository;

import java.util.List;

public class SupplierController implements ISupplierController {
    private final ISupplierRepository repo;

    public SupplierController(ISupplierRepository repo) {
        this.repo = repo;
    }

    public String addSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password) {
        if (deliveryCost < 0) {
            return "Delivery cost cannot be negative!";
        }

        Supplier supplier = new Supplier(brandOfShoes, countryOfOrigin, name, email, deliveryCost, password);
        boolean created = repo.addSupplier(supplier);

        return (created ? "Success" : "Fail");
    }

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

    public String getSupplierByEmail(String email) {
        Supplier supplier = repo.findByEmail(email);
        return (supplier == null ? "Supplier was not found" : supplier.toString());
    }
}