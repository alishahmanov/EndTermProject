package repositories.interfaces;

import models.Supplier;
import java.util.List;
import java.util.Optional;

public interface ISupplierRepository {
    Optional<Supplier> findByEmail(String email);
    List<Supplier> getAllSuppliers();
    boolean addSupplier(Supplier supplier);
}