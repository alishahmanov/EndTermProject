package services;

import exceptions.OrderNotFoundException;
import models.Client;
import models.Order;
import models.Shoes;
import models.User;
import models.enums.Role;
import repositories.interfaces.IOrderRepository;
import services.interfaces.IOrderService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.EnumSet;

public class OrderService implements IOrderService {
    private final IOrderRepository repo;

    // Разрешённые роли для удаления заказа
    private static final EnumSet<Role> DELETE_ALLOWED_ROLES = EnumSet.of(Role.ADMIN, Role.MANAGER);

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

        int totalPrice = items.stream()
                .mapToInt(Shoes::getPrice)
                .sum();

        Order order = new Order(null, client, items, "Pending");
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
