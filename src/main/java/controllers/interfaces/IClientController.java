package controllers.interfaces;

public interface IClientController {
    String addClient(String name, String email, String password, boolean gender, int size, int amountOfMoney);
    String getAllClients();
    String getClientByEmail(String email);
}