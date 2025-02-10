package services;

import exceptions.OrderNotFoundException;
import factorieses.OrderFactory;
import models.Client;
import models.Order;
import models.Shoes;
import models.User;
import models.enums.Role;
import repositories.interfaces.IOrderRepository;
import services.interfaces.IOrderService;

import java.util.List;
import java.util.EnumSet;

public class OrderService implements IOrderService {
    private final IOrderRepository repo;
    private static final EnumSet<Role> DELETE_ALLOWED_ROLES = EnumSet.of(Role.ADMIN);

    public OrderService(IOrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public Order getOrderById(Long id) {
        return repo.getOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<Order> getAllOrders() {
        return repo.getAllOrders();
    }

    @Override
    public boolean createOrder(Client client, List<Shoes> items) {
        if (client.getRole() != Role.CLIENT) {
            throw new SecurityException("Access Denied: Only CLIENT can create orders.");
        }
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item.");
        }

        Order order = OrderFactory.createOrder(client, items);
        return repo.addOrder(order);
    }

    @Override
    public boolean deleteOrderById(Long id, User user) {
        if (!DELETE_ALLOWED_ROLES.contains(user.getRole())) {
            throw new SecurityException("Access Denied: Only ADMIN or MANAGER can delete orders.");
        }

        return repo.deleteOrderById(id);
    }
}
