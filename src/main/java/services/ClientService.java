package services;
import models.enums.Role;
import models.Client;
import repositories.interfaces.IClientRepository;
import services.interfaces.IClientService;

import java.util.List;
import java.util.Optional;

public class ClientService implements IClientService {
    private final IClientRepository repo;

    public ClientService(IClientRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Client> getClientByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public List<Client> getAllClients() {
        return repo.getAllClients();
    }

    @Override
    public boolean addClient(String name, String email, String password, boolean gender, int size, int amountOfMoney) {
        Client client = new Client(name, email, password, Role.CLIENT, gender, size, amountOfMoney);
        return repo.addClient(client);
    }
}
