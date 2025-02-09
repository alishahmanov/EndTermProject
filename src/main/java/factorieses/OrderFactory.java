package factorieses;

import models.Client;
import models.Order;
import models.Shoes;

import java.util.List;

public class OrderFactory {
    public static Order createOrder(Client client, List<Shoes> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item.");
        }
        return new Order(null, client, items, "Pending");
    }
}