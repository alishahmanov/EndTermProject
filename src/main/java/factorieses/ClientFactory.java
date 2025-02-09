package factorieses;

import models.Client;
import models.enums.Role;

public class ClientFactory {
    public static Client createClient(String name, String email, String password, boolean gender, int size, int amountOfMoney) {
        return new Client(name, email, password, Role.CLIENT, gender, size, amountOfMoney);
    }
}
