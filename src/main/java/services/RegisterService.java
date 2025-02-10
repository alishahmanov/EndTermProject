package services;

import factorieses.SupplierFactory;
import factorieses.ClientFactory;
import models.Client;
import models.Supplier;
import repositories.interfaces.IClientRepository;
import repositories.interfaces.ISupplierRepository;
import services.interfaces.IRegisterService;

public class RegisterService implements IRegisterService {
    private final IClientRepository clientRepo;
    private final ISupplierRepository supplierRepo;

    public RegisterService(IClientRepository clientRepo, ISupplierRepository supplierRepo) {
        this.clientRepo = clientRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public String registerClient(String name, String email, String password, boolean gender, int size, int amountOfMoney) {
        if (clientRepo.findByEmail(email).isPresent()) {
            return " Client with this email already exists!";
        }

        if (size <= 0) {
            throw new IllegalArgumentException(" Size must be greater than 0!");
        }

        if (amountOfMoney < 0) {
            throw new IllegalArgumentException("Amount of money cannot be negative!");
        }

        Client client = ClientFactory.createClient(name, email, password, gender, size, amountOfMoney);
        boolean created = clientRepo.addClient(client);
        return created ? "Client registered successfully!" : " Failed to register client!";
    }

    @Override
    public String registerSupplier(String brandOfShoes, String countryOfOrigin, String name, String email, int deliveryCost, String password) {
        if (supplierRepo.findByEmail(email).isPresent()) {
            return " Supplier with this email already exists!";
        }

        if (deliveryCost < 0) {
            throw new IllegalArgumentException("Delivery cost cannot be negative!");
        }

        Supplier supplier = SupplierFactory.createSupplier(name, email, password, brandOfShoes, countryOfOrigin, deliveryCost);
        boolean created = supplierRepo.addSupplier(supplier);
        return created ? " Supplier registered successfully!" : "Failed to register supplier!";
    }
}
