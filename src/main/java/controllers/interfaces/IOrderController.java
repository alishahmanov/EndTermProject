package controllers.interfaces;

import models.Client;
import models.Order;
import models.Shoes;
import models.User;

import java.util.List;

public interface IOrderController {
    String createOrder(Client client, List<Shoes> items);
    String getOrderById(Long id);
    String getAllOrders();
    String deleteOrderById(Long id, User user);
}
