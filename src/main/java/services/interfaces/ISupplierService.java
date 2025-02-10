package services.interfaces;

import models.Supplier;
import java.util.List;
import java.util.Optional;

public interface ISupplierService {
    Optional<Supplier> getSupplierByEmail(String email); // ✅ Должен возвращать Optional
    List<Supplier> getAllSuppliers();
    boolean addSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password);
}
