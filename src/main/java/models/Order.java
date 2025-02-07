package models;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private Client client;
    private List<Shoes> items;
    private LocalDateTime orderDate;
    private String status;
    private int totalPrice;

    public Order(Long id, Client client, List<Shoes> items, String status) {
        setId(id);
        setClient(client);
        setItems(items);
        setOrderDate(LocalDateTime.now());
        setStatus(status);
        setTotalPrice(calculateTotalPrice());
    }

    private int calculateTotalPrice() {
        return items.stream()
                .mapToInt(Shoes::getPrice)
                .sum();
    }


    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Shoes> getItems() {
        return items;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setItems(List<Shoes> items) {
        this.items = items;
        setTotalPrice(calculateTotalPrice());
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + id +
                "\nClient: " + client.getName() +
                "\nTotal Price: $" + totalPrice +
                "\nStatus: " + status +
                "\nOrder Date: " + orderDate +
                "\nItems: " + items;
    }
}
