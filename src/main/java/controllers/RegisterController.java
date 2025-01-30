package controllers;

import controllers.interfaces.IRegisterController;
import services.interfaces.IRegisterService;

public class RegisterController implements IRegisterController {
    private final IRegisterService registerService;

    public RegisterController(IRegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    public String registerClient(String name, String email, String password, boolean gender, int size, int amountOfMoney) {
        try {
            return registerService.registerClient(name, email, password, gender, size, amountOfMoney);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @Override
    public String registerSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password) {
        try {
            return registerService.registerSupplier(brandOfShoes, countryOfOrigin, name, email, deliveryCost, password);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
