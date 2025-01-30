package services.interfaces;

public interface IRegisterService {
    String registerClient(String name, String email, String password, boolean gender, int size, int amountOfMoney);
    String registerSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password);
}
