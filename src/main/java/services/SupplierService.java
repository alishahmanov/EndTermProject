package services;

import factorieses.SupplierFactory;
import models.Supplier;
import repositories.interfaces.ISupplierRepository;
import services.interfaces.ISupplierService;

import java.util.List;
import java.util.Optional;

public class SupplierService implements ISupplierService {
    private final ISupplierRepository repo;

    public SupplierService(ISupplierRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Supplier> getSupplierByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return repo.getAllSuppliers();
    }

    @Override
    public boolean addSupplier(String brandofshoes, String countryOfOrigin, String name, String email, int deliveryCost, String password) {
        Supplier supplier = SupplierFactory.createSupplier(name, email, password, brandofshoes, countryOfOrigin, deliveryCost);
        return repo.addSupplier(supplier);
    }
}
