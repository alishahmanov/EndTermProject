package repositories.interfaces;

import models.Client;
import java.util.List;
import java.util.Optional;

public interface IClientRepository {
    Optional<Client> findByEmail(String email);  // Изменено с Client -> Optional<Client>
    List<Client> getAllClients();
    boolean addClient(Client client);
}