package repositories.interfaces;

import models.Client;
import java.util.List;

public interface IClientRepository {
    Client findByEmail(String email);
    List<Client> getAllClients();
    boolean addClient(Client client);
}
