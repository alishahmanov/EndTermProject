package repositories.interfaces;

import models.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {
    boolean addOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrders();
    boolean deleteOrderById(Long id);
}
