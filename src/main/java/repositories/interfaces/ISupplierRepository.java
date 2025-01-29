package repositories.interfaces;

import models.Supplier;
import java.util.List;

public interface ISupplierRepository {
    Supplier findByEmail(String email);
    List<Supplier> getAllSuppliers();
    boolean addSupplier(Supplier supplier);
}