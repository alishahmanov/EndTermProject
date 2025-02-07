package services.interfaces;

import models.Client;
import models.Order;
import models.Shoes;
import models.User;

import java.util.List;

public interface IOrderService {
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    boolean createOrder(Client client, List<Shoes> items);
    boolean deleteOrderById(Long id, User user);
}
