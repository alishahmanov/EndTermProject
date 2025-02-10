package repositories;

import data.interfaces.IDB;
import models.Client;
import models.Order;
import models.Shoes;
import repositories.interfaces.IOrderRepository;
import factorieses.ShoesFactory;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRepository implements IOrderRepository {
    private final IDB db;

    public OrderRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders(client_id, order_date, status, total_price) VALUES (?, ?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setLong(1, order.getClient().getId());
            st.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            st.setString(3, order.getStatus());
            st.setInt(4, order.getTotalPrice());

            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long orderId = generatedKeys.getLong(1);
                    order.setId(orderId);
                    return true;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        String sql = """
    SELECT o.id AS order_id, o.order_date, o.status, o.total_price, 
           c.id AS client_id, c.name AS client_name, c.email AS client_email, c.gender, c.size, c.amountofmoney,
           s.id AS shoe_id, s.brand, s.material, s.color, s.season_of_shoes, s.size AS shoe_size, s.price
    FROM orders o
    JOIN clients c ON o.client_id = c.id
    JOIN order_items oi ON o.id = oi.order_id
    JOIN shoes s ON oi.shoe_id = s.id
    WHERE o.id = ?
    """;


        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Client client = new Client(
                        rs.getString("client_name"),
                        rs.getString("client_email"),
                        "",
                        models.enums.Role.CLIENT,
                        rs.getBoolean("gender"),
                        rs.getInt("size"),
                        rs.getInt("amountofmoney")
                );



                List<Shoes> shoesList = new ArrayList<>();
                do {
                    Shoes shoe = ShoesFactory.createShoes(
                            rs.getBoolean("gender"),
                            rs.getString("brand"),
                            rs.getString("material"), // Передаём как строку, фабрика сама обработает
                            rs.getString("season"),  // Передаём как строку, фабрика сама обработает
                            rs.getString("color"),
                            rs.getInt("shoe_size"),
                            rs.getInt("price"),
                            rs.getString("availability") // Если это строка, фабрика обработает
                    );
                    shoesList.add(shoe);
                } while (rs.next());


                Order order = new Order(
                        rs.getLong("order_id"),
                        client,
                        shoesList,
                        rs.getString("status")
                );

                return Optional.of(order);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            return new ArrayList<ResultSet>().stream()
                    .map(r -> {
                        try {
                            return new Order(
                                    rs.getLong("id"),
                                    null,
                                    new ArrayList<>(),
                                    rs.getString("status")
                            );
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }
    }


    @Override
    public boolean deleteOrderById(Long id) {
        String sql = "DELETE FROM orders WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);
            return st.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }
    }
}
