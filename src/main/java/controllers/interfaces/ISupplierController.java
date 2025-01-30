package controllers.interfaces;

public interface ISupplierController {
    String addSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password);
    String getAllSuppliers();
    String getSupplierByEmail(String email);
}