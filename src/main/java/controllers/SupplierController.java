package controllers;

import exceptions.SupplierNotFoundException;
import models.Supplier;
import services.interfaces.ISupplierService;

import java.util.List;

public class SupplierController {
    private final ISupplierService service;

    public SupplierController(ISupplierService service) {
        this.service = service;
    }

    public String addSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password) {
        if (brandOfShoes == null || brandOfShoes.trim().isEmpty()) return " Brand cannot be empty!";
        if (countryOfOrigin == null || countryOfOrigin.trim().isEmpty()) return " Country cannot be empty!";
        if (name == null || name.trim().isEmpty()) return " Name cannot be empty!";
        if (email == null || !email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) return " Invalid email format!";
        if (deliveryCost < 0) return " Delivery cost cannot be negative!";

        boolean created = service.addSupplier(brandOfShoes, countryOfOrigin, name, email, deliveryCost, password);
        return created ? "Supplier added successfully!" : " Failed to add supplier.";
    }

    public List<Supplier> getAllSuppliers() {
        return service.getAllSuppliers();
    }

    public Supplier getSupplierByEmail(String email) {
        return service.getSupplierByEmail(email)
                .orElseThrow(() -> new SupplierNotFoundException(email));
    }
}
