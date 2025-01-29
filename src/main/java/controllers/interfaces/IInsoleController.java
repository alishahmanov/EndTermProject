package controllers.interfaces;

public interface IInsoleController {
    String addInsole(String type, String material, int size, int price, String availability);
    String getAllInsoles();
    String getInsole(int id);
}
