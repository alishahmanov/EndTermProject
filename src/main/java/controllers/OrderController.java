package controllers;

import models.Client;
import models.Order;
import models.Shoes;
import models.User;
import services.interfaces.IOrderService;
import controllers.interfaces.IOrderController;

import java.util.List;

public class OrderController implements IOrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String createOrder(Client client, List<Shoes> items) {
        boolean created = orderService.createOrder(client, items);
        return created ? "✅ Order created successfully!" : "❌ Failed to create order.";
    }

    @Override
    public String getOrderById(Long id) {
        try {
            Order order = orderService.getOrderById(id);
            return order.toString(); // ✅ Теперь возвращает Order.toString()
        } catch (Exception e) {
            return "❌ Order not found!";
        }
    }

    @Override
    public String getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders.isEmpty() ? "No orders found!" : orders.toString();
    }

    @Override
    public String deleteOrderById(Long id, User user) {
        try {
            boolean deleted = orderService.deleteOrderById(id, user);
            return deleted ? "✅ Order deleted successfully." : "❌ Failed to delete order.";
        } catch (SecurityException e) {
            return e.getMessage();
        }
    }
}
