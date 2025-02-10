package controllers;

import exceptions.ClientNotFoundException;
import models.Client;
import services.interfaces.IClientService;

import java.util.List;

public class ClientController {
    private final IClientService service;

    public ClientController(IClientService service) {
        this.service = service;
    }

    public String addClient(String name, String email, String password, boolean gender, int size, int amountOfMoney) {
        if (name == null || name.trim().isEmpty()) return "Name cannot be empty!";
        if (email == null || !email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) return "Invalid email format!";
        if (size <= 0) return "Size must be greater than 0!";
        if (amountOfMoney < 0) return " Amount of money cannot be negative!";

        boolean created = service.addClient(name, email, password, gender, size, amountOfMoney);
        return created ? "Client added successfully!" : "Failed to add client.";
    }

    public List<Client> getAllClients() {
        return service.getAllClients();
    }

    public Client getClientByEmail(String email) {
        return service.getClientByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(email));
    }
}
