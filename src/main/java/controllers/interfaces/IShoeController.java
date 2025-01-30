package controllers.interfaces;

public interface IShoeController {
    String addShoe(String brand, String material, String color, String seasonOfShoes, boolean gender, int size, int price, String availability);    String getAllShoes();
    String getShoe(int id);
}
