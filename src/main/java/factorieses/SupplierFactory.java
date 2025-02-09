package factorieses;

import models.Supplier;
import models.enums.Role;

public class SupplierFactory {
    public static Supplier createSupplier(String name, String email, String password, String brandOfShoes, String countryOfOrigin, int deliveryCost) {
        return new Supplier(null, name, email, password, brandOfShoes, countryOfOrigin, deliveryCost, Role.SUPPLIER);
    }
}
