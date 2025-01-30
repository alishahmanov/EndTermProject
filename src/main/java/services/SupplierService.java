package services;

import exceptions.SupplierNotFoundException;
import models.Supplier;
import repositories.interfaces.ISupplierRepository;
import services.interfaces.ISupplierService;

import java.util.List;

public class SupplierService implements ISupplierService {
    private final ISupplierRepository repo;

    public SupplierService(ISupplierRepository repo) {
        this.repo = repo;
    }

    @Override
    public Supplier getSupplierByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new SupplierNotFoundException(email));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return repo.getAllSuppliers();
    }

    @Override
    public boolean addSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password) {
        if (repo.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Supplier with this email already exists!");
        }

        if (deliveryCost < 0) {
            throw new IllegalArgumentException("Delivery cost cannot be negative!");
        }

        Supplier supplier = new Supplier(brandOfShoes, countryOfOrigin, name, email, deliveryCost, password);
        return repo.addSupplier(supplier);
    }
}
