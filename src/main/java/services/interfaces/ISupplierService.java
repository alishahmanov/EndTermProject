package services.interfaces;

import models.Supplier;
import java.util.List;

public interface ISupplierService {
    Supplier getSupplierByEmail(String email);
    List<Supplier> getAllSuppliers();
    boolean addSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password);
}
