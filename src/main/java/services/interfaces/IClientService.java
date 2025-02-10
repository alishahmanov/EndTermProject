package services.interfaces;

import models.Client;
import java.util.List;
import java.util.Optional;

public interface IClientService {
    Optional<Client> getClientByEmail(String email); // ✅ Теперь возвращает Optional<Client>
    List<Client> getAllClients();
    boolean addClient(String name, String email, String password, boolean gender, int size, int amountOfMoney);
}
