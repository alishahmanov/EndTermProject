package repositories;

import data.interfaces.IDB;
import models.Client;
import repositories.interfaces.IClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.enums.Role;

public class ClientRepository implements IClientRepository {
    private final IDB db;

    public ClientRepository(IDB db) {
        this.db = db;
    }

    public Optional<Client> findByEmail(String email) {
        return getAllClients().stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();
    }


    @Override
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(new Client(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role").toUpperCase()),
                        rs.getBoolean("gender"),
                        rs.getInt("size"),
                        rs.getInt("amountofmoney")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return clients;
    }

    @Override
    public boolean addClient(Client client) {
        String sql = "INSERT INTO clients(name, email, password, role, gender, size, amountofmoney) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, client.getName());
            st.setString(2, client.getEmail());
            st.setString(3, client.getPassword());
            st.setString(4, client.getRole().name());
            st.setBoolean(5, client.isGender());
            st.setInt(6, client.getSize());
            st.setInt(7, client.getAmountOfMoney());

            return st.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }
    }
}