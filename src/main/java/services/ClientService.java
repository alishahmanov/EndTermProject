package services;

import exceptions.ClientNotFoundException;
import models.Client;
import repositories.interfaces.IClientRepository;
import services.interfaces.IClientService;

import java.util.List;

public class ClientService implements IClientService {
    private final IClientRepository repo;

    public ClientService(IClientRepository repo) {
        this.repo = repo;
    }

    @Override
    public Client getClientByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(email));
    }

    @Override
    public List<Client> getAllClients() {
        return repo.getAllClients();
    }

    @Override
    public boolean addClient(String name, String email, String password, boolean gender, int size, int amountOfMoney) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0!");
        }
        if (amountOfMoney < 0) {
            throw new IllegalArgumentException("Amount of money cannot be negative!");
        }

        Client client = new Client(name, email, password, gender, size, amountOfMoney);
        return repo.addClient(client);
    }
}
