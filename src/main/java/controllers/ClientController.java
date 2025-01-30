package controllers;
import exceptions.ClientNotFoundException;
import models.Client;
import controllers.interfaces.IClientController;
import repositories.interfaces.IClientRepository;

import java.util.List;

public class ClientController implements IClientController {
    private final IClientRepository repo;

    public ClientController(IClientRepository repo) {
        this.repo = repo;
    }

    public String addClient(String name, String email, String password, boolean gender, int size, int amountOfMoney) {
        if (size <= 0) {
            return "Size must be greater than 0!";
        }

        if (amountOfMoney < 0) {
            return "Amount of money cannot be negative!";
        }

        Client client = new Client(name, email, password, gender, size, amountOfMoney);
        boolean created = repo.addClient(client);

        return (created ? "Success" : "Fail");
    }

    public String getAllClients() {
        List<Client> clients = repo.getAllClients();

        if (clients.isEmpty()) {
            return "No clients found!";
        }

        StringBuilder response = new StringBuilder();
        for (Client client : clients) {
            response.append(client).append("\n");
        }

        return response.toString();
    }

    public String getClientByEmail(String email) {
        return repo.findByEmail(email)
                .map(Client::toString)
                .orElseThrow(() -> new ClientNotFoundException(email));
    }

}
