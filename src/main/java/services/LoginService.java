package services;

import exceptions.ClientNotFoundException;
import exceptions.SupplierNotFoundException;
import models.Client;
import models.Supplier;
import repositories.interfaces.IClientRepository;
import repositories.interfaces.ISupplierRepository;
import services.interfaces.ILoginService;

public class LoginService implements ILoginService {
    private final IClientRepository clientRepo;
    private final ISupplierRepository supplierRepo;

    public LoginService(IClientRepository clientRepo, ISupplierRepository supplierRepo) {
        this.clientRepo = clientRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public String login(String email, String password) {
        Client client = clientRepo.findByEmail(email).orElse(null);
        if (client != null) {
            if (client.getPassword().equals(password)) {
                return "Client login successful!";
            } else {
                return "Incorrect password for client!";
            }
        }

        Supplier supplier = supplierRepo.findByEmail(email).orElse(null);
        if (supplier != null) {
            if (supplier.getPassword().equals(password)) {
                return "Supplier login successful!";
            } else {
                return "Incorrect password for supplier!";
            }
        }
        throw new RuntimeException("User with email: " + email + " not found!");
    }
}

